import os
import shutil

exec1 = "C:\\Users\\VHLAB\\Desktop\\Pratica em Pesquisa - 25-1\\videos"
exec2 = "C:\\Users\\VHLAB\\Desktop\\Pratica\\output"
for pasta in os.listdir(exec1): #entra nas pastas de cada vídeo (1,2,3,4,5,6,7,8,9,10)
    for emocao in os.listdir(exec1+"\\"+pasta): #entra em cada pasta emoção (angry, happy e sad)
        for nivel in os.listdir(exec1+"\\"+pasta+"\\"+emocao): #entra em cada nível de emoção (1, 2, 3)
            try:
                if not os.path.exists(exec2 +"\\"+pasta+"\\"+emocao+"\\"+nivel):
                    os.makedirs(exec2 +"\\"+pasta+"\\"+emocao+"\\"+nivel)
            except OSError:
                print('Error: Creating directory of data')
            for video in os.listdir(exec1 +"\\"+pasta+"\\"+ emocao+"\\"+nivel):
                comando = ' -f ' + '"' +  exec1+"\\"+pasta+"\\"+emocao+ "\\" +nivel +"\\"+video+'"'
                os.system('"C:\\OpenFace\\FeatureExtraction.exe' + comando + ' -out_dir ' + exec2 +"\\"+pasta+"\\"+emocao+"\\"+nivel + '"')

## Remove arquivos gerados pelos OpenFace
for pasta in os.listdir(exec2):
    for emocao in os.listdir(exec2+"\\"+pasta):
        for nivel in os.listdir(exec2+"\\"+pasta+"\\"+emocao):
            for arq in os.listdir(exec2+"\\"+pasta+"\\"+emocao+"\\"+nivel):
                listas = arq.split('.')
                try:                
                    if 'csv' not in listas[1]:
                        os.remove(exec2+"\\"+pasta + "\\"+emocao+"\\"+nivel + '/' + arq)
                except IndexError:
                    shutil.rmtree(exec2+"\\"+pasta + "\\"+emocao+"\\"+nivel + '/' + arq, ignore_errors=True)
