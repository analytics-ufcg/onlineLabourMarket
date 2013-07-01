args = commandArgs(T)

dir.oferta = args[1]
dir.demanda = args[2]
dir.retribuicao = args[3]

files.oferta = list.files(dir.oferta, full.names=T, pattern=".csv")
files.demanda = list.files(dir.demanda, full.names=T, pattern=".csv")
files.retribuicao = list.files(dir.retribuicao, full.names=T, pattern=".csv")

demanda.passado.ranking = read.csv("ranking/passado/mergedemandaRanking.csv", header=F)
oferta.passado.ranking = read.csv("ranking/passado/mergeofertaRanking.csv", header=F)
retribuicao.passado.ranking = read.csv("ranking/passado/mergeretribuicaoRanking.csv", header=F)


calcula.top5 = function(files, index2) {
  habilidades = c()
  valores = c()
  i = 1
  for(f in files) {
    dados = read.csv(f)    
    valor = sum(dados$Elance[1:index2]) + sum(dados$Guru[1:index2])    
    habilidade = gsub("[a-z]+/[a-z]+/","", f)
    habilidade = gsub(".csv", "", habilidade)
    habilidades[i] = habilidade
    valores[i] = valor
    i = i + 1
  }
  saida = as.data.frame(cbind(habilidades, valores))
  saida[,2] = as.double(as.character(saida$valores))
  saida = saida[order(saida$valores, saida$habilidades, decreasing=T),]
  saida = saida[, 1]
  saida = as.data.frame(saida)
  colnames(saida) = c("V1")
  return(saida)
}

calcula.porcent.mudancas = function(files, past.rank) {
  ranking.anterior = past.rank
  conta.mudancas = 0
  for(i in seq(1:125)) {
    ranking.atual = calcula.top5(files, i)
    if(all.equal(ranking.atual, ranking.anterior) != TRUE) {
      conta.mudancas = conta.mudancas + 1
    }
    ranking.anterior = ranking.atual
  }
  porcent.mudancas = conta.mudancas / 125
  return(round(porcent.mudancas, 2))
}

calcula.porcent.mudancas2 = function(files, past.rank) {
  ranking.anterior = past.rank
  conta.mudancas = 0
  for(i in seq(1:125)) {
    ranking.atual = calcula.top5(files, i)
    if(all.equal(ranking.atual, ranking.anterior) != TRUE) {
      conta.mudancas = conta.mudancas + 1
    }
    #ranking.anterior = ranking.atual
  }
  porcent.mudancas = conta.mudancas / 125
  return(round(porcent.mudancas, 2))
}

print(calcula.porcent.mudancas(files.oferta, oferta.passado.ranking))
print(calcula.porcent.mudancas2(files.oferta, oferta.passado.ranking))

print(calcula.porcent.mudancas(files.demanda, demanda.passado.ranking))
print(calcula.porcent.mudancas2(files.demanda, demanda.passado.ranking))

print(calcula.porcent.mudancas(files.retribuicao, retribuicao.passado.ranking))
print(calcula.porcent.mudancas2(files.retribuicao, retribuicao.passado.ranking))