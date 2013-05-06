# import to use command-line arguments

import sys
import time

# Produce a ranking of skills based in how many different tasks it appears on
# Autor: Alberto, Ricardo
def buildRanking(inputFileName,idIndex,skillIndex,sep="&|&",skillSep=";"):
    ranking = []
    inputFile = open(inputFileName, "r")
    for line in inputFile:
        currentLine = line.split(sep)
        currentSkills = currentLine[skillIndex].rstrip().split(skillSep)
        for skill in currentSkills:
            if(skill in ranking):
                continue
            else:
                ranking.append(skill)
    inputFile.close()
    return ranking

	
	
	
# Formata arquivo de demanda com erros identificados:
# autor: Giovani
def formatDemandFile(fileInName, fileOutName):
    SEP = '&|&'
    saida = open(fileOutName, "w")
    entrada = open(fileInName, "r")
    for row in entrada:
        observationTime, jobtitle,uniqueJobId,jobType,jobRequesterName,jobUniqueRequesterId, expirationTime, price,price2, proposals, description, category ,keywords,requestRate = row.split(SEP)
        keywords = str(keywords)[1:]
        uniqueJobId = str(uniqueJobId)[:-2] + " "
        saida.write(observationTime + SEP + jobtitle +SEP+ uniqueJobId +SEP+ jobType +SEP+ jobRequesterName +SEP+ jobUniqueRequesterId +SEP+ expirationTime +SEP+ price +SEP+ price2 +SEP+ proposals +SEP+ description +SEP+ category +SEP+ keywords + SEP + requestRate)
    entrada.close()
    saida.close()

# Gera arquivos com as frequencias em cada tempo para cada skill
# autor: Giovani
def generateTimeLineFilesFromWorkers(fileInName):
    SEP = '&|&'
    EXTENSAO = ".txt"
    SEP_OUT = ";"
    entrada = open(fileInName, "r")
    for row in entrada:
        timeStamp, skill, currentFreq = row.split(SEP)
        timeStamp = time.ctime(int(timeStamp))
        saida = open(skill + EXTENSAO, "a")
        saida.write(timeStamp + SEP_OUT + currentFreq)
        saida.close()
    entrada.close()
