date.from.timestamp =  function(ts){
  return(strptime(structure(ts, class=c("POSIXt", "POSIXct")), "%Y-%m-%d %H"))
}

files = list.files("requesters", full.names=T)

for(f in files) {
  read.table(f, sep=";")
  dados$V3 = date.from.timestamp(dados$V1)
  ag = with(dados, aggregate(V2, list(as.numeric(V3)), sum))
  colnames(ag) = c("timestamp", "frequencia")
  write.table(file=gsub("requesters/", "", f), ag, row.names=F, col.names=F, sep=",")
}