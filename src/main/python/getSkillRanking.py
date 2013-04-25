# Sprint 01 - US 01
#
# * Input: arquivo com demanda coletada do mercado de trabalho
# * Output: arquivo com ranking de habilidades
# * Autor: Alberto, Ricardo
# * Executar: python getSkillRanking.py inputDemandfileName outputRankingFileName idColumnIndex skillColumnIndex
# * Teste: python onlinelabourmarkettest.py

import onlinelabourmarket
import sys

def main():
    inputFileName = str(sys.argv[1])
    outputFileName = str(sys.argv[2])
    idIndex = int(sys.argv[3])
    skillIndex = int(sys.argv[4])
    
    ranking = onlinelabourmarket.buildRanking(inputFileName, idIndex, skillIndex)
    outputFile = open(outputFileName,"w")
    for w in sorted(ranking, key=ranking.get, reverse=True):
        outputFile.write(w +";" + str(ranking[w])+"\n")
    outputFile.close()


if  __name__ =='__main__':main()
