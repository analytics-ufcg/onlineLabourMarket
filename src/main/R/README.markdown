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