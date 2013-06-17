require("forecast")

args = commandArgs(T)

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

DURACAO.DIA = 86400

dia.inicial = 1362193200
dia.final = 1372906800

dir = args[1]

files = list.files(dir, full.names=T, pattern=".csv")

qnt.dias.a.prever = ((as.numeric(date.from.timestamp(dia.final)) 
                      -as.numeric(date.from.timestamp(dia.inicial)))/DURACAO.DIA) + 1

generate.predict = function(file) {
  dados = read.csv(file)
  elance = dados$Elance
  guru = dados$Guru
  elance.predict = predict.data.frame(elance)
  guru.predict = predict.data.frame(guru)
  #Plataformas = elance.predict[, 1] + guru.predict[, 1]
  #rmse = elance.predict[, 2] + guru.predict[, 2]
  #porcent.Elance = (elance.predict[, 1]/
  #                           (elance.predict[, 1] + guru.predict[, 1]))
  #porcent.Guru = (guru.predict[, 1]/
  #                           (elance.predict[, 1] + guru.predict[, 1]))
  return(cbind(elance.predict[, 1], elance.predict[, 2], 
               guru.predict[, 1], guru.predict[, 2]))
}

predict.data.frame = function(value) {
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
  return(as.data.frame(cbind(predictions, rmse)))
}

generate.timestamps = function() {
  timestamps = c()
  for(i in seq(1:125)) {
    timestamps[i] = dia.inicial + (DURACAO.DIA * (i - 1))
  }
  return(timestamps)
}

iterate.files = function(files) {
  for(f in files) {
    output = cbind(generate.timestamps(), generate.predict(f))
    colnames(output) = c("timestamp", "Elance", "Elance.RMSE", "Guru", "Guru.RMSE")
    nome.skill = gsub("^[a-z]+/", "", f)
    write.csv(file=paste("futuro", nome.skill, sep="/"),
              output, row.names=F, quote=F)
  }
}

iterate.files(files)