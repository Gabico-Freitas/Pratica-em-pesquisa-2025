import pandas as pd
df=pd.read_parquet("F:\\UsGuri\\df_compilado_faces_real.parquet",engine="fastparquet")
print(df)
for linha in df.iterrows():
    print(linha)
    break
