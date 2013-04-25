# Sprint 02 - US 06
#
# * Input: arquivo com demanda coletada do mercado de trabalho
# * Output: arquivo com demanda coletada do mercado de trabalho sem erros nas colunas
# * Autor: Giovani
# * Executar: python formataSkills.py inputDemandfileName outputDemandFileName
# * Teste: python onlinelabourmarkettest.py

import sys
import onlinelabourmarket
        
if __name__ == '__main__':
    onlinelabourmarket.formatDemandFile(sys.argv[1])
pass
