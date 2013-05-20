#Carrega script que gera os arquivos=============================================================
source("buildRetributionFiles.R")

#funcao que testa se o arquivo relacionado a uma Categoria=======================================
#e gerado de forma correta comparando-o com o data.frame esperado================================
assert.equals = function(input.path, expected) {
  generating.csv.file(input.path)
  generated.csv = read.csv(gsub(".txt", ".csv", input.path))
  generated.csv$time = (generated.csv$time)
  generated.csv$mediana = (generated.csv$mediana)
  generated.csv$lista = (generated.csv$lista)
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
expected.admin = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                        as.numeric(date.from.timestamp((1362095550)))), 
                            mediana=c(500,500), lista = c("50; 500; 500; 500; 500; 500; 500; 500; 
                                                          500; 500; 500; 500; 500; 500; 500; 1000; 
                                                          50; 500; 500; 500; 500; 500; 500; 500; 500;
                                                          500; 500; 500; 500; 500; 500; 1000",
                                                          "50; 100; 500; 500; 500; 500; 500; 500; 500;
                                                          500; 500; 500; 50; 100; 500; 500; 500; 500; 
                                                          500; 500; 500; 500; 500; 500; 500; 500; 500"))

expected.design = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                    as.numeric(date.from.timestamp((1358581475)))), 
                             mediana=c(23.5,32), lista = c("20; 20; 25; 30; 35; 20; 20; 22; 30; 35"
                                                           ,"20; 25; 32; 40; 40; 20; 25; 35; 40"))

expected.engineering = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                         as.numeric(date.from.timestamp((1356973761)))), 
                                  mediana=c(500,500), lista = c("30; 500; 500; 30; 500; 500"
                                                                ,"500; 500; 500; 500; 500; 5000; 5000; 
                                                                500; 500; 500; 500; 500; 5000; 5000"))

expected.finance = data.frame(time=c(as.numeric(date.from.timestamp(1351126786)), 
                                     as.numeric(date.from.timestamp((1358083362)))), 
                              mediana=c(500,500), lista = c("500; 500; 500; 500"
                                                            ,"500; 500; 500; 500; 500; 500; 500; 500"))

expected.it = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                as.numeric(date.from.timestamp((1362095789)))), 
                         mediana=c(20,40), lista = c("20; 20; 20; 25; 20; 20; 20; 25"
                                                     ,"25; 40; 40; 40; 25; 40; 40; 50"))

expected.legal = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                   as.numeric(date.from.timestamp((1355924481)))), 
                            mediana=c(500,500), lista = c("500;500","40; 100; 500; 500; 500;
                                                            500; 500; 500; 500; 10000; 10000;
                                                            40; 100; 500; 500; 500; 500; 500; 
                                                            500; 500; 10000; 10000"))

expected.sales = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                   as.numeric(date.from.timestamp((1362095789)))), 
                            mediana=c(70,600), lista = c("25; 70; 70; 75; 500; 25; 70; 70; 100; 500"
                                                           ,"500; 500; 700; 1000; 500; 500; 700; 1000"))

expected.writing = data.frame(time=c(as.numeric(date.from.timestamp(1351114780)), 
                                     as.numeric(date.from.timestamp((1362095789)))), 
                              mediana=c(30,22), lista = c("25; 30; 35; 45; 25; 30; 30; 40"
                                                          ,"20; 21; 22; 50; 21; 22; 50; 50; 100"))

#todos os prints abaixo devem retornar TRUE para que o script esteja certo=======================
print(assert.equals(admin.txt, expected.admin))
print(assert.equals(design.txt, expected.design))
print(assert.equals(engineering.txt, expected.engineering))
print(assert.equals(finance.txt, expected.finance))
print(assert.equals(it.txt, expected.it))
print(assert.equals(legal.txt, expected.legal))
print(assert.equals(sales.txt, expected.sales))
print(assert.equals(writing.txt, expected.writing))