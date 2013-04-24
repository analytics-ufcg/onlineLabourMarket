# import to use command-line arguments

import sys

# Produce a ranking of skills based in how many different tasks it appears on
def contFrequencia(inputFileName,idIndex,skillIndex,sep="&|&",skillSep=";"):
    print inputFileName
    processedIDs = []
    ranking = {}
    inputFile = open(str(inputFileName), "r")
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


def main():
    inputFileName = str(sys.argv[1])
    outputFileName = str(sys.argv[2])
    idIndex = sys.argv[3]
    skillIndex = sys.argv[4]
    
#    rankingCode = GetSkillRanking()
    
    ranking = contFrequencia(inputFileName, idIndex, skillIndex)
    outputFile = open(outputFileName,"w")
    for w in sorted(ranking, key=ranking.get, reverse=True):
        outputFile.write(w +";" + str(ranking[w])+"\n")
    outputFile.close()


if  __name__ =='__main__':main()
