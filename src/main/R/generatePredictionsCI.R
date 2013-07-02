require("forecast")

args = commandArgs(T)

dir = args[1]

files = list.files(dir, full.names=T, pattern=".csv")

generate.predict = function(file) {
  dados = read.csv(file)
  elance = dados$Elance
  guru = dados$Guru
  elance.predict = predict.data.frame(elance)
  guru.predict = predict.data.frame(guru)
  return(cbind(elance.predict[, 1], guru.predict[, 1]))
}

predict.data.frame = function(value) {
  ts = ts(value,frequency=7)
  fit = auto.arima(ts)
  fcast = forecast(fit, h=125, level=c(95, 99))
  predictions = as.data.frame(fcast)[,1]
  return(as.data.frame(predictions))
}

iterate.files = function(files) {
  for(f in files) {
    output = generate.predict(f)
    colnames(output) = c("Elance", "Guru")
    nome.skill = gsub("^[a-z]+/", "", f)
    write.csv(file=paste("futuro", nome.skill, sep="/"),
              output, row.names=F, quote=F)
  }
}

iterate.files(files)