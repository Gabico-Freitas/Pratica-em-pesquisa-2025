import pandas as pd
df=pd.read_parquet("C:\\Users\\vhlab\\Downloads\\df_compilado_faces_real.parquet",engine="fastparquet")
#print(df)
# colunas=df.columns.to_list()
# print(colunas)
df_filtrado = df[df["Pasta_Nivel_1"] == "video_2"]
df_filtrado = df_filtrado[df_filtrado["Pasta_Nivel_2"] == "happy"]
df_filtrado = df_filtrado[df_filtrado["Pasta_Nivel_3"] == "level_3"]
df_filtrado = df_filtrado[df_filtrado["Nome_Arquivo"] == "002_blendshape_data.csv"]
df_filtrado = df_filtrado.iloc[89]
df_filtrado.to_csv("D:\\Pratica\\frame_90_video_2_Happy_3_mp4_02.csv", index=False)
#print(df_filtrado)
# for arq in df["Pasta_Nivel_1"]: 
#     if arq == "video_2":
#         for emocao in df["Pasta_Nivel_2"]:
#             if emocao == "happy":
#                 print(emocao)
#                 break
#         break
        

#print(df["Pasta_Nivel_1"]=="video_2")
# for linha in df.iterrows():
#     print(linha)
#     break
