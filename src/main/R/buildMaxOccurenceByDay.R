date.from.timestamp =  function(ts){
 return(strptime(as.POSIXct(ts, origin="1970-1-1", "GMT"), "%Y-%m-%d"))
}

args = commandArgs(TRUE)
source.dir = as.character(args[1])
plataforma = as.character(args[2])

files = list.files(source.dir, full.names=T, pattern=".txt")

generating.csv.file = function(f) {
  dados = read.table(f, sep=";")
  dados$V3 = date.from.timestamp(dados$V1)
  ag = with(dados, aggregate(V2, list(as.numeric(V3)), max))
  colnames(ag) = c("timestamp", plataforma)
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