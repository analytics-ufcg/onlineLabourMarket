date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d"))
}

files = list.files("requesters", full.names=T)

for(f in files) {
  dados = read.table(f, sep=";")
  dados$V3 = date.from.timestamp(dados$V1)
  ag = with(dados, aggregate(V2, list(as.numeric(V3)), sum))
  colnames(ag) = c("timestamp", "Elance")
  nome = gsub("requesters/", "", f)
  nome = gsub(".txt", ".csv", nome)
  write.csv(file=nome, ag, row.names=F, col.names=F, sep=",", quote=F)
}