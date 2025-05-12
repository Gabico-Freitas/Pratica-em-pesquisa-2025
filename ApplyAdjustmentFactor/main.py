import pandas as pd
from pathlib import Path

aumap = {
    "browinnerup": "AU01",
    "browouterleft": "AU02",
    "browouterright": "AU02",
    "browdownleft": "AU04",
    "browdownright": "AU04",
    "eyewideleft": "AU05",
    "eyewideright": "AU05",
    "cheeksquintleft": "AU06",
    "cheeksquintright": "AU06",
    "eyesquintleft": "AU07",
    "eyesquintright": "AU07",
    "nosesneerleft": "AU09",
    "nosesneerright": "AU09",
    "mouthupperleft": "AU10",
    "mouthupperright": "AU10",
    "mouthsmileleft": "AU12",
    "mouthsmileright": "AU12",
    "mouthdimpleleft": "AU14",
    "mouthdimpleright": "AU14",
    "mouthfrownleft": "AU15",
    "mouthfrownright": "AU15",
    "mouthshrugupper": "AU17",
    "mouthstretchleft": "AU20",
    "mouthstretchright": "AU20",
    "mouthpucker": "AU23",
    "mouthshruglower": "AU25",
    "jawopen": "AU26",
    "eyeblinkleft": "AU45",
    "eyeblinkright": "AU45"
}

ref = pd.read_csv("factors.csv", index_col=0)
input_dir = Path("input")
for csv in input_dir.glob("*.csv"):
    df = pd.read_csv(csv)
    for (column_name, column_content) in df.items():
        matching_au = aumap.get(column_name.lower())
        if(matching_au == None):
            continue
        factor = ref.at[csv.name, matching_au]
        if(factor == "#DIV/0!"):
            continue 
        df[column_name] = column_content.mul(float(factor))
    df.to_csv("output\\ALTERED_"+csv.name, index=False)
        