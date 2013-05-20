require("stats")

args = commandArgs(T)

date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

DURACAO.DIA = 86400

dia.inicial = 1362193200
dia.final = 1372906800 + DURACAO.DIA

retribuicao = as.character(args[1])

#usado no n.ahead do predict
#quantidade de dias a partir da primeira data do futuro
#ate a ultima data a ser prevista
calcula.n.ahead = function(ts) {
  return((as.numeric(date.from.timestamp(dia.final))
          -as.numeric(date.from.timestamp(ts+DURACAO.DIA)))/DURACAO.DIA)
}

generate.predict = function(f) {
  dados = read.csv(f, header=T)
  valores = dados$Elance
  if(nrow(subset(dados, dados$Elance != 500)) > 0) {
    auto.regressao = ar.burg(valores, order.max=125, AIC=T)
    pred.total = predict(auto.regressao, se.fit=T, n.ahead=126)
    return(cbind(pred.total$pred, pred.total$se))    
  } else {
    return(cbind(valores, rep(0:0, each=126)))
  }
}

generate.timestamps = function() {
  timestamps = c()
  for(i in seq(1:126)) {
    timestamps[i] = dia.inicial + (DURACAO.DIA * (i - 1))
  }
  return(timestamps)
}

predicao.retribuicao = generate.predict(retribuicao)
col.timestamps.retribuicao = generate.timestamps()

output.retribuicao = cbind(col.timestamps.retribuicao, predicao.retribuicao)

output.retribuicao = as.data.frame(output.retribuicao)

colnames(output.retribuicao) = c("timestamp", "Elance", "erro")

nome.skill = gsub("^[a-z]+/[a-z]+/", "", retribuicao)
path.futuro = "futuro/retribuicao/"

write.csv(file=paste(path.futuro, nome.skill, sep=""), output.retribuicao, row.names=F, quote=F)