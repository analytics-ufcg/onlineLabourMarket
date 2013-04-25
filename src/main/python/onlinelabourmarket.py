# import to use command-line arguments

import sys

# Produce a ranking of skills based in how many different tasks it appears on
# Autor: Alberto, Ricardo
def buildRanking(inputFileName,idIndex,skillIndex,sep="&|&",skillSep=";"):
    processedIDs = []
    ranking = {}
    inputFile = open(inputFileName, "r")
    for line in inputFile.readlines():
        currentLine = line.split(sep)
        currentID = currentLine[idIndex]
        if(currentID in processedIDs):
            continue;
        else:
            processedIDs.append(currentID)
            currentSkills = currentLine[skillIndex].rstrip().split(skillSep)
            for skill in currentSkills:
                if(skill in ranking):
                    ranking[skill] += 1
                else:
                    ranking[skill] = 1
    inputFile.close()
    return ranking

# Formata arquivo de demanda com erros identificados:
# autor: Giovani
def formatDemandFile(arqEntrada):
    SEP = '&|&'
    saida = open("elanceEditado.txt", "w")
    for row in open(arqEntrada, "r"):
        observationTime, jobtitle,uniqueJobId,jobType,jobRequesterName,jobUniqueRequesterId, expirationTime, price,price2, proposals, description, category ,keywords,requestRate = row.split(SEP)
        keywords = str(keywords).replace("S", "", 1)
        uniqueJobId = str(uniqueJobId).replace("\"", "")
        saida.write(observationTime + SEP + jobtitle +SEP+ uniqueJobId +SEP+ jobType +SEP+ jobRequesterName +SEP+ jobUniqueRequesterId +SEP+ expirationTime +SEP+ price +SEP+ price2 +SEP+ proposals +SEP+ description +SEP+ category +SEP+ keywords + SEP + requestRate)
    saida.close()
