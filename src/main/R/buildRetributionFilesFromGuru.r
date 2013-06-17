date.from.timestamp =  function(ts){
  return(strptime(as.POSIXct(ts, origin="1970-1-1", "GMT"), "%Y-%m-%d"))
}

args = commandArgs(TRUE)
source.dir = as.character(args[1])
plataforma = as.character(args[2])

files = list.files(source.dir, full.names=T, pattern=".csv")

#output.dir = paste(source.dir, "byDay", sep="/")
#dir.create(output.dir, showWarnings=F)

generating.csv.file = function(f) {
  dados = read.csv(f)
  dados$V4 = date.from.timestamp(dados$currentTime)
  dados$lista = as.character(gsub(" ", "", as.character(dados$lista)))
  ag = with(dados, aggregate(lista, list(as.numeric(V4)), paste))
  rm(dados)
  for(i in c(1:nrow(ag))) {
    numeric.vector = as.numeric(unlist(strsplit(ag$x[[i]], split=";")))    
    ag$x[i] = median(numeric.vector, na.rm=T)
    ag$new[i] = list(numeric.vector)
  }
  colnames(ag) = c("timestamp", plataforma, "lista")
  ag$Guru = unlist(ag$Guru)
  ag$lista = gsub("[c(]", "", as.character(ag$lista))
  ag$lista = gsub(")", "", ag$lista) 
  ag$lista = gsub(",", ";", ag$lista)
  ag$lista = gsub("\n", "", ag$lista)
  ag$lista = gsub(" ", "", ag$lista)
  #nome.file = paste(output.dir, gsub(source.dir, "", f), sep="/")
  write.csv(ag, file=f, quote=F, row.names=F)
  rm(ag)
}

iterate.files = function(files) {
  for(f in files) {
    generating.csv.file(f)
  }  
}

iterate.files(files)