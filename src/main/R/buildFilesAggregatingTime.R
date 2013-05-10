date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

args = commandArgs(TRUE)
source.dir = as.character(args[1])

files = list.files(source.dir, full.names=T, pattern=".txt")

generating.csv.file = function(f) {
  dados = read.table(f, sep=";")
  dados$V3 = date.from.timestamp(dados$V1)
  ag = with(dados, aggregate(V2, list(as.numeric(V3)), sum))
  colnames(ag) = c("timestamp", "Elance")
  nome = f
  nome = gsub(".txt", ".csv", nome)
  write.csv(file=nome, ag, row.names=F, quote=F)
}

iterate.files = function(files) {
  for(f in files) {
    generating.csv.file(f)
  }  
}

iterate.files(files)