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