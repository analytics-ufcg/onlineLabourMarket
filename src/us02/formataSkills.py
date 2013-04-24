import sys

def formataColunas(arqEntrada):
    SEP = '&|&'
    saida = open("elanceEditado.txt", "w")
    for row in open(arqEntrada, "r"):
        observationTime, jobtitle,uniqueJobId,jobType,jobRequesterName,jobUniqueRequesterId, expirationTime, price,price2, proposals, description, category ,keywords,requestRate = row.split(SEP)
        keywords = str(keywords).replace("S", "", 1)
        uniqueJobId = str(uniqueJobId).replace("\"", "")
        saida.write(observationTime + SEP + jobtitle +SEP+ uniqueJobId +SEP+ jobType +SEP+ jobRequesterName +SEP+ jobUniqueRequesterId +SEP+ expirationTime +SEP+ price +SEP+ price2 +SEP+ proposals +SEP+ description +SEP+ category +SEP+ keywords + SEP + requestRate)
    saida.close()
        
if __name__ == '__main__':
    formataColunas(sys.argv[1])    
pass
