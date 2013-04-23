# import to use command-line arguments
import sys

def processaArquivo(arquivoEntrada,arquivoSaida,lista,separador):
    arqIn = open(arquivoEntrada,"r")
    arqOut = open(arquivoSaida,"w")
    for colNome in lista:
        arqOut.write(colNome)
        if(colNome!=lista[-1]):
            arqOut.write(",")
        else:
            arqOut.write("\n")
    for linha in arqIn.readlines():
        listaDados = linha.split(separador)
        for dado in listaDados:
            arqOut.write(dado)
            if(dado!=listaDados[-1]):
                arqOut.write(",")
    arqIn.close()
    arqOut.close()

def extrairAtributos(arquivoEn,arquivoSa,listaPos,sep):
    arqEn = open(arquivoEn,"r")
    arqSa = open(arquivoSa,"w")
    for linha in arqEn.readlines():
        listaDados = linha.split(sep)
        for pos in listaPos:
            arqSa.write(listaDados[pos])
            if(pos!=listaPos[-1]):
                arqSa.write(",")
        arqSa.write("\n")
    arqEn.close()
    arqSa.close()

def contFrequencia(arquivoIn,arquivoOut,separador):
    lista = []
    skills = []
    freq = []
    arq = open(arquivoIn,"r")
    for linha in arq.readlines():
        temp = linha.split(",")
        if(temp[0] in lista):
            continue;
        else:
            lista.append(temp[0])
            sks = temp[1].split(separador)
            for sk in sks:
                sk = sk.replace("\n","")
                if(sk in skills):
                    val = skills.index(sk)
                    freq[val] = freq[val]+1
                else:
                    skills.append(sk)
                    freq.append(1)
    arq.close()
    lista=[]
    for i in range(len(skills)):
        lista.append(skills[i]+";"+str(freq[i])+"\n")
    lista = sorted(lista, cmp=compare)
    arqS = open(arquivoOut,"w")
    for k in lista:
        arqS.write(k)
    arqS.close()
 
def compare(x, y):
    temp = x.split(";")
    temp2 = y.split(";")
    num = int(temp[1])
    num2 = int(temp2[1])
    return num2-num


extrairAtributos(str(sys.argv[1]),str(sys.argv[2]), [2,12],"&|&")
contFrequencia(str(sys.argv[2]),str(sys.argv[2]),";")
#contFrequencia("testeUnidade.txt","saida2.txt",";")
