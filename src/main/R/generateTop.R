args = commandArgs(T)

dir.oferta = args[1]
dir.demanda = args[2]
dir.retribuicao = args[3]

inicial = as.numeric(args[4])
final = as.numeric(args[5])


files.oferta = list.files(dir.oferta, full.names=T, pattern=".csv")
files.demanda = list.files(dir.demanda, full.names=T, pattern=".csv")
files.retribuicao = list.files(dir.retribuicao, full.names=T, pattern=".csv")


calcula.top5 = function(files, index1, index2) {
  plataformas = c()
  valores = c()
  i = 1
  for(f in files) {
    dados = read.csv(f)
    if(length(grep("merge", f)) == 1) {
      if(length(grep("Broadcasting", f)) == 1) {
        if(length(grep("oferta", f)) == 1) {
          valor = sum(dados$Elance[27:56]) + sum(dados$Guru[27:56])  
        } else {
          valor = sum(dados$Elance[24:53]) + sum(dados$Guru[24:53])
        }  
      } else {
        valor = sum(dados$Elance[index1:index2]) + sum(dados$Guru[index1:index2])
      }
    } else {
      valor = sum(dados$Elance[index1:index2]) + sum(dados$Guru[index1:index2])
    }
    plataforma = gsub("[a-z]+/[a-z]+/","", f)
    plataforma = gsub(".csv", "", plataforma)
    plataformas[i] = plataforma
    valores[i] = valor
    i = i + 1
  }
  saida = as.data.frame(cbind(plataformas, valores))
  saida[,2] = as.double(as.character(saida$valores))
  saida = saida[order(saida$valores, saida$plataformas, decreasing=T),]
  print(saida)
  nome = gsub("[a-zA-Z &]+.csv$", "", files[1])
  nome = gsub("/", "", nome)
  nome = paste(nome, "Ranking.csv", sep="")
  write.table(file=nome, saida[,1], col.names=F, row.names=F, quote=F)
}

calcula.top5(files.oferta, inicial, final)
calcula.top5(files.demanda, inicial, final)
calcula.top5(files.retribuicao, inicial, final)