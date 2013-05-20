require("stats")

args = commandArgs(T)

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

DURACAO.DIA = 86400

dia.inicial = as.numeric(args[1])
dia.final = as.numeric(args[2])

oferta = as.character(args[3])
demanda = as.character(args[4])

qnt.dias.a.prever = ((as.numeric(date.from.timestamp(dia.final)) 
                     -as.numeric(date.from.timestamp(dia.inicial)))/DURACAO.DIA) + 1

#usado no n.ahead do predict
#quantidade de dias a partir da primeira data do futuro
#ate a ultima data a ser prevista
calcula.n.ahead = function(ts) {
  return((as.numeric(date.from.timestamp(dia.final))
          -as.numeric(date.from.timestamp(ts+DURACAO.DIA)))/DURACAO.DIA)
}

generate.predict = function(f) {
  dados = read.csv(f)
  n.steps = calcula.n.ahead(dados$timestamp[nrow(dados)])
  valores = dados$Elance
  auto.regressao = ar.burg(valores, order.max=nrow(dados)-1, AIC=T)
  pred.total = predict(auto.regressao, se.fit=T, n.ahead=n.steps)
  return(pred.total$pred[(n.steps-qnt.dias.a.prever+1):n.steps])
}

generate.timestamps = function() {
  timestamps = c()
  for(i in seq(1:qnt.dias.a.prever)) {
    timestamps[i] = dia.inicial + (DURACAO.DIA * (i - 1))
  }
  return(timestamps)
}

predicao.oferta = generate.predict(oferta)
col.timestamps.oferta = generate.timestamps()

output.oferta = cbind(col.timestamps.oferta, predicao.oferta)

output.oferta = as.data.frame(output.oferta)

colnames(output.oferta) = c("timestamp", "Elance")

nome.skill = gsub("^[a-z]+/[a-z]+/", "", oferta)
path.futuro = "futuro/oferta/"


write.csv(file=paste(path.futuro, nome.skill, sep=""), output.oferta, row.names=F, quote=F)

predicao.demanda = generate.predict(demanda)
col.timestamps.demanda = generate.timestamps()

output.demanda = cbind(col.timestamps.demanda, predicao.demanda)

output.demanda = as.data.frame(output.demanda)

colnames(output.demanda) = c("timestamp", "Elance")

nome.skill = gsub("^[a-z]+/[a-z]+/", "", demanda)
path.futuro = "futuro/demanda/"


write.csv(file=paste(path.futuro, nome.skill, sep=""), output.demanda, row.names=F, quote=F)