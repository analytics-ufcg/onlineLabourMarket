#Carrega script que gera os arquivos=============================================================
source("buildFilesAggregatingTime.R")

#funcao que testa se o arquivo relacionado a uma Categoria=======================================
#e gerado de forma correta comparando-o com o data.frame esperado================================
assert.equals = function(input.path, expected) {
  generating.csv.file(input.path)
  generated.csv = read.csv(gsub(".txt", ".csv", input.path))
  generated.csv$timestamp = (generated.csv$timestamp)
  generated.csv$Elance = (generated.csv$Elance)
  file.remove(gsub(".txt", ".csv", input.path))
  return(all.equal(generated.csv, expected))
}

path.files = args[1]
#path dos arquivos de entrada====================================================================
admin.txt = paste(args[1],"Admin Support.txt",sep="/")
design.txt = paste(args[1],"Design & Multimedia.txt",sep="/")
engineering.txt = paste(args[1],"Engineering & Manufacturing.txt",sep="/")
finance.txt = paste(args[1],"Finance & Management.txt",sep="/")
it.txt = paste(args[1],"IT & Programming.txt",sep="/")
legal.txt = paste(args[1],"Legal.txt",sep="/")
sales.txt = paste(args[1],"Sales & Marketing.txt",sep="/")
writing.txt = paste(args[1],"Writing & Translation.txt",sep="/")

#data.frames que representam os arquivos .csv gerados pela script testado========================
expected.admin = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                        as.numeric(date.from.timestamp((1351137293)))), 
                            Elance=c(144,96))

expected.design = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                         as.numeric(date.from.timestamp(1354414993))), 
                                         Elance=c(367,354))

expected.engineering = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                              as.numeric(date.from.timestamp(1351130510))), 
                                  Elance=c(15, 18))

expected.finance = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                          as.numeric(date.from.timestamp(1361643925))), 
                              Elance=c(9, 30))

expected.it = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                     as.numeric(date.from.timestamp(1361889617))), 
                         Elance=c(621, 570))

expected.legal = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                        as.numeric(date.from.timestamp(1361616340))), 
                                        Elance=c(24, 12))

expected.sales = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                        as.numeric(date.from.timestamp(1361243189))), 
                            Elance=c(150, 177))

expected.writing = data.frame(timestamp=c(as.numeric(date.from.timestamp(1351114848)), 
                                          as.numeric(date.from.timestamp(1360255815))), 
                              Elance=c(201, 228))

#todos os prints abaixo devem retornar TRUE para que o script esteja certo=======================
print(assert.equals(admin.txt, expected.admin))
print(assert.equals(design.txt, expected.design))
print(assert.equals(engineering.txt, expected.engineering))
print(assert.equals(finance.txt, expected.finance))
print(assert.equals(it.txt, expected.it))
print(assert.equals(legal.txt, expected.legal))
print(assert.equals(sales.txt, expected.sales))
print(assert.equals(writing.txt, expected.writing))