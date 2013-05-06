# Sprint 02 - US 06
#
# * Input: arquivo dos workers
# * Output: arquivos com timestamp;frequencia para cada habilidade encontrada no arquivo de entrada
# * Autor: Giovani
# * Executar: python geraSkillsFromWorkers.py Elance-Category.data

import sys
import onlinelabourmarket
        
if __name__ == '__main__':
    onlinelabourmarket.generateTimeLineFilesFromWorkers(sys.argv[1])
pass
