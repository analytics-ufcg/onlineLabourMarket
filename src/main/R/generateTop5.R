args = commandArgs(T)

date.from.timestamp = function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

DURACAO.DIA = 86400

dir.oferta.passado = args[1]
dir.demanda.passado = args[2]
dir.retribuicao.passado = args[3]

dir.oferta.futuro = args[4]
dir.demanda.futuro = args[5]
dir.retribuicao.futuro = args[6]


files.oferta.passado = list.files(dir.oferta.passado, full.names=T, pattern=".csv")
files.demanda.passado = list.files(dir.demanda.passado, full.names=T, pattern=".csv")
files.retribuicao.passado = list.files(dir.retribuicao.passado, full.names=T, pattern=".csv")

files.oferta.futuro = list.files(dir.oferta.futuro, full.names=T, pattern=".csv")
files.demanda.futuro = list.files(dir.demanda.futuro, full.names=T, pattern=".csv")
files.retribuicao.futuro = list.files(dir.retribuicao.futuro, full.names=T, pattern=".csv")


calcula.top5 = function(files, index1, index2) {
  plataformas = c()
  valores = c()
  i = 1
  for(f in files) {
    dados = read.csv(f)
    valor = sum(dados$Elance[index1:index2])
    plataforma = gsub("[a-z]+/[a-z]+/","", f)
    plataforma = gsub(".csv", "", plataforma)
    plataformas[i] = plataforma
    valores[i] = valor
    i = i + 1
  }
  saida = as.data.frame(cbind(plataformas, valores))
  saida = saida[order(saida$valores,decreasing=T),]
  nome = gsub("[a-zA-Z &]+.csv$", "", files[1])
  nome = gsub("/", "", nome)
  nome = paste(nome, "Ranking.csv", sep="")
  write.table(file=nome, saida[1:5,1], col.names=F, row.names=F, quote=F)
}

calcula.top5(files.oferta.passado, 95, 125)
calcula.top5(files.demanda.passado, 95, 125)
calcula.top5(files.retribuicao.passado, 95, 125)

calcula.top5(files.oferta.futuro, 1, 30)
calcula.top5(files.demanda.futuro, 1, 30)
calcula.top5(files.retribuicao.futuro, 1, 30)