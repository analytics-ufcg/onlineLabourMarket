require("stats")

args = commandArgs(T)

DURACAO.DIA = 86400

dia.inicial = args[1]
dia.final = args[2]

dir.oferta = args[3]
dir.demanda = args[4]

files.oferta = list.files(dir.oferta, full.names=T, pattern=".csv")
files.demanda = list.files(dir.demanda, full.names=T, pattern=".csv")

qnt.dias.a.prever = (as.numeric(date.from.timestamp(dia.final)) 
                     -as.numeric(date.from.timestamp(dia.inicial)))/DURACAO.DIA

#usado no n.ahead do predict
#quantidade de dias a partir da primeira data do futuro
#ate a ultima data a ser prevista
calcula.n.ahead = function(ts) {
  return((as.numeric(date.from.timestamp(dia.final))
          -as.numeric(date.from.timestamp(ts+DURACAO.DIA)))/DURACAO.DIA)
}




if(dia.inicial == dia.final) {
  
} else {
  
}

generate.predict(f) {
  dados = read.csv(f)
  n.steps = calcula.n.ahead(dados$timestamp[nrow(dados)])
  valores = dados$Elance
  auto.regressao = ar.burg(valores, order.max=nrow(dados)-1, AIC=T)
  pred.total = predict(auto.regressao, se.fit=T, n.ahead=n.steps)
  return(pred.total$pred[(n.steps-qnt.dias.a.prever+1):n.steps])
}

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

iterate.files = function(files) {
  for(f in files) {
    generate.predict(f)
  }  
}

iterate.files(files)