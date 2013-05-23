Código R/Rscript
================

1. plotTimeSeries.R
 * Input: diretório com vários arquivos com a frequencia das habilidades ao longo do tempo 
 * Output: um gráfico de linha
 * Autor: Giovani
 * Executar: ./plotTimeSeries.R path_to_input_files 
 
2. buildFilesAggregatingTime.R
 * Input: diretório nomeado como "requesters" contendo os arquivos a serem processados
 * Output: arquivos com o timestamp,plataforma para cada habilidade
 * Autor: Giovani
 * Executar: Rscript ./buildFilesAggregatingTime.R nomeDoDiretorioContendoArquivos
 
 3. buildRetributionFiles.R
 * Input: diretório que contém os arquivos a serem processados com o formato .csv
 * Output: arquivos com timestamp,plataforma,lista para cada habilidade agrupados por dia
 * Autor: Giovani e Elias
 * Executar: Rscript buildRetributionFiles.R diretorioContendoArquivos
 
 4. generatePredictions.R
 * Input: diretórios de workers, requesters e retribuicao contendo apenas os arquivos .csv a serem processados
 * Output: arquivos com o timestamp,plataforma,erro das predicoes para cada habilidade
 * Autor: Giovani
 * Executar: Rscript generatePredictions.R passado/oferta passado/demanda passado/retribuicao
 * Um detalhe importante na execução do script é que o script deve estar em um diretório que contenha a
 * seguinte estrutura de diretórios:
 * passado/oferta, passado/demanda, passado/retribuicao, futuro/oferta, futuro/demanda e futuro/retribuicao
 
 5. generateTop5.R
 * Input: diretório que contém os arquivos a serem processados com o formato .csv
 * Output: os rankings das 5 melhores habilidades 30 dias correntes até a data atual e 30 dias após a data atual
 * Autor: Giovani
 * Executar: Rscript generateTop5.R passado/oferta passado/demanda passado/retribuicao futuro/oferta futuro/demanda futuro/retribuicao
 * Detalhe:  script deve estar em um diretório que contenha a seguinte estrutura de diretórios:
 * passado/oferta, passado/demanda, passado/retribuicao, futuro/oferta, futuro/demanda e futuro/retribuicao