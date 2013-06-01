require("forecast")

args = commandArgs(T)

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

DURACAO.DIA = 86400

dia.inicial = 1362193200
dia.final = 1372906800

dir.oferta = args[1]
dir.demanda = args[2]
dir.retribuicao = args[3]

files.oferta = list.files(dir.oferta, full.names=T, pattern=".csv")
files.demanda = list.files(dir.demanda, full.names=T, pattern=".csv")
files.retribuicao = list.files(dir.retribuicao, full.names=T, pattern=".csv")

qnt.dias.a.prever = ((as.numeric(date.from.timestamp(dia.final)) 
                      -as.numeric(date.from.timestamp(dia.inicial)))/DURACAO.DIA) + 1

#quantidade de dias a partir da primeira data do futuro
#ate a ultima data a ser prevista
calcula.n.ahead = function(ts) {
  return((as.numeric(date.from.timestamp(dia.final))
          -as.numeric(date.from.timestamp(ts+DURACAO.DIA)))/DURACAO.DIA)
}

generate.predict = function(f) {
  value = read.csv(f)
  value = value$Elance
  ts = ts(value,frequency=7)
  ts.train = window(ts, end=(end(ts)*0.8))
  ts.test = window(ts, start=(end(ts)*0.8))
  fit = auto.arima(ts.train)
  fcast = forecast(fit, h=length(ts.test))
  acuracia = as.data.frame(accuracy(fcast, ts.test))
  rmse = rep(acuracia[2, 1], qnt.dias.a.prever)
  fit = auto.arima(ts)
  fcast = forecast(fit, h=qnt.dias.a.prever)
  predictions = as.data.frame(fcast)[,1]
  return(cbind(predictions, rmse))
}

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

generate.timestamps = function() {
  timestamps = c()
  for(i in seq(1:125)) {
    timestamps[i] = dia.inicial + (DURACAO.DIA * (i - 1))
  }
  return(timestamps)
}

iterate.files = function(files) {
  predictions = c()
  for(f in files) {
    output = cbind(generate.timestamps(), generate.predict(f))
    output = as.data.frame(output)
    colnames(output) = c("timestamp", "Elance", "erro")
    nome.skill = gsub("^[a-z]+/", "", f)
    write.csv(file=paste("futuro", nome.skill, sep="/"),
              output, row.names=F, quote=F)
  }
}

iterate.files(files.oferta)
iterate.files(files.demanda)
iterate.files(files.retribuicao)