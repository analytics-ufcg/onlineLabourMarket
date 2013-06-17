args = commandArgs(T)
dir = args[1]

files.guru = list.files(dir, full.names=T, pattern=".csv")

merge.platforms = function(elance.file, guru.file) {  
  if(elance.file == "elance/oferta/Broadcasting.csv" |
       elance.file == "elance/demanda/Broadcasting.csv" |
       elance.file == "elance/retribuicao/Broadcasting.csv") {
    guru = read.csv(guru.file)    
    #return(cbind(guru[, c(1,2)], rep(0, nrow(guru)),guru[, 2],
    #             rep(0, nrow(guru)), 
    #             rep(100.00, nrow(guru))))
    return(cbind(guru[, 1], rep(0, nrow(guru)), guru[, 2]))
  }    
  elance = read.csv(elance.file)
  guru = read.csv(guru.file)
  merge = merge(elance[, c(1, 2)], guru[, c(1, 2)], all.x=T)
  merge$Guru = replace(merge$Guru, which(is.na(merge$Guru)), 0)
  if(ncol(elance) == 3) {
    
  }
  #value = merge$Elance + merge$Guru
  #return(cbind(merge$timestamp, value, merge$Elance, merge$Guru,
  #             ((merge$Elance / value) * 100), 
  #             ((merge$Guru / value) * 100)))
  return(merge)
}


iterate.files = function(files) {
  for(f in files) {
    nome.skill = gsub("^[a-z]+/", "", f)
    elance = paste("elance", nome.skill, sep="/")
    output = merge.platforms(elance, f)
    output = as.data.frame(output)
    #colnames(output) = c("timestamp", "Plataformas", "Elance", "Guru", 
    #                     "porcent.Elance", "porcent.Guru")
    colnames(output) = c("timestamp", "Elance", "Guru")
    write.csv(file=paste("merge", nome.skill, sep="/"),
              output, row.names=F, quote=F)
  }
}

iterate.files(files.guru)