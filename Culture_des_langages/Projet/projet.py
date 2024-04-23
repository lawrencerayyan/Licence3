import csv
import requests
import pandas as pd
from bs4 import BeautifulSoup
from sklearn.model_selection import train_test_split    
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score, confusion_matrix
from sklearn.preprocessing import StandardScaler
from sklearn.tree import DecisionTreeClassifier
from sklearn.tree import export_graphviz
import graphviz
from joblib import dump 

def comestible(url):
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        category = soup.find('div', class_='cat_link').text.strip()
        if category == 'Poisonous Mushrooms':
            return 'P'
        elif category == 'Edible Mushrooms':
            return 'E'
        elif category == 'Inedible Mushrooms':
            return 'I'
        else:
            return'structure de page inconnue'
    return 'requete non reussie'

   
def color(url):
    try:
        response = requests.get(url)
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            profile_div = soup.find('div', class_='mprofile')
            if profile_div:
                color_paragraph = profile_div.find(lambda tag: tag.name == 'p' and 'Color:' in tag.text)
                if color_paragraph:
                    color_links = color_paragraph.find_all('a')
                    if color_links:  
                        colors = [link.get_text(strip=True) for link in color_links]
                        return '-'.join(colors)
    except Exception as e:
        return ""


def shape(url):
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        mprofile_div = soup.find('div', class_='mprofile')
        if mprofile_div:
            shape_tag = mprofile_div.find('strong', string='Shape:')
            if shape_tag:
                shape = shape_tag.find_next('a').text.strip().replace(' ', '')
                return shape
    return ''

def surface(url):
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        mprofile_div = soup.find('div', class_='mprofile')
        if mprofile_div:
            surface_tag = mprofile_div.find('strong', string='Surface:')
            if surface_tag:
                surface = surface_tag.find_next('a').text.strip()
                return surface
    return ''

def csvv(url):
    type = comestible(url)
    color_info = color(url)
    shape_info = shape(url)
    surface_info = surface(url)
    # Formater la chaîne CSV
    csv_info = f"{type},{color_info},{shape_info},{surface_info}"
    return csv_info


#Fonction pour récupérer les liens de champignons
def get_champignion_links(): 
    kl = requests.get("https://ultimate-mushroom.com/mushroom-alphabet.html")
    soup = BeautifulSoup(kl.text, "html.parser")
    champignion_links = []

    # Trouver tous les liens vers les pages de champignons
    for a_tag in soup.find_all('a', href=True):
        href = a_tag.get('href', '')
        if 'ultimate-mushroom.com/' in href:
            champignion_links.append(href)


    return champignion_links

#Fonction pour écrire dans le fichier CSV
def write_to_csv(file_path, data):
    with open(file_path,'a', newline='', encoding='utf-8') as csvfile:
        csv_writer = csv.writer(csvfile)
        csv_writer.writerow(data)

#Fonction principale pour parcourir les pages et générer le fichier CSV
def generate_csv_for_champignions():
    champignion_links = get_champignion_links()

    # Écrire la première ligne dans le fichier CSV
    first_row = ["Edible", "Color", "Shape", "Surface"]
    write_to_csv("champignons.csv", first_row)

    for link in champignion_links:
        champignion_type = comestible(link)
        champignion_color = color(link)
        champignion_shape = shape(link)
        champignion_surface = surface(link)

        # Construire une liste avec les caractéristiques du champignon
        champignion_data = [champignion_type,champignion_color, champignion_shape, champignion_surface]

        # Écrire dans le fichier CSV
        write_to_csv("champignons.csv", champignion_data)

#Appel a la fonction de creation de csv
# generate_csv_for_champignions()


champignons = pd.read_csv("champignons.csv")

# Vérifier les dimensions du DataFrame
print("Dimensions du DataFrame :", champignons.shape)

# Vérifier les noms des colonnes
print("Noms des colonnes :", champignons.columns)


# # if champignons.shape == (209, 4) and all(champignons.columns == ["Edible", "Color", "Shape", "Surface"]):
# #     print("Le jeu de données possède 209 lignes et 4 attributs avec les bons noms.")
# # else:
# #     print("Le jeu de données ne correspond pas aux attentes.")

# Inspecter les données de la colonne "Edible" et compter les occurrences de chaque valeur
print(champignons['Edible'].value_counts(dropna=False))

# Remplacer les valeurs 'E', 'I' et 'P' par les valeurs numériques 0, 1 et 2 respectivement
champignons['Edible'] = champignons['Edible'].replace({'E': 0, 'I': 1, 'P': 2})
print(champignons.head())
# # Vérifier que les remplacements ont été effectués correctement
# print(champignons['Edible'].value_counts(dropna=False))

# # Remplacer les valeurs manquantes par -1
champignons['Edible'] = champignons['Edible'].fillna(-1)

# # Vérifier les comptages des valeurs après le remplacement des valeurs manquantes
print(champignons['Edible'].value_counts(dropna=False))

# # Remplacer les valeurs manquantes par 0 dans la colonne "Shape"
# champignons['Shape'].fillna("", inplace=True)

# # Remplacer les valeurs manquantes par 0 dans la colonne "Surface"
# champignons['Surface'].fillna("", inplace=True)
# # Obtenez la liste de toutes les valeurs uniques de la colonne "Shape"
unique_shapes = pd.unique(champignons['Shape'].str.split("-").explode().dropna())

# # Pour chaque valeur unique, créez une nouvelle colonne et remplissez-la avec 1 si la valeur apparaît dans le champ "Shape" de la ligne respective
for shape in unique_shapes:
    champignons[shape] = champignons['Shape'].str.contains(shape).fillna(False).astype(int)
print(champignons.head())
def create_indicator_columns(df, column_name):
    unique_values = pd.unique(df[column_name].str.split("-").explode().dropna())
    for value in unique_values:
        df[value] = df[column_name].str.contains(value, na=False).astype(int)

# # Créer des colonnes indicatrices pour la colonne "Surface"
create_indicator_columns(champignons, "Surface")
# create_indicator_columns(champignons, "Shape")

# # Supprimer les colonnes "Shape" et "Surface"
champignons.drop(columns=["Shape", "Surface"], axis=1,inplace=True)

# # Vérifier les dimensions de la DataFrame
print("Dimensions de la DataFrame après suppression des colonnes Shape et Surface :", champignons.shape)
print(champignons.head())

# # # Obtenez la liste de toutes les valeurs uniques de la colonne "Color"
unique_colors = pd.unique(champignons["Color"].str.split("-").explode().dropna())

print(f"Nombre de couleurs : {len(unique_colors)}")
print(unique_colors)

# # # Afficher la liste des couleurs individuelles
# # # print("Liste des couleurs individuelles présentes dans le jeu de données :")
# for color in unique_colors:
#     print(color)
# # # Pale White,Yellow,Brown,Pink,Purple,Tan,Orange,Gray,Red,Dark


# # # Liste des couleurs individuelles avec leurs codes RGB
data = {
    "Color": unique_colors,
    "R": [255, 255, 255, 165, 255, 128, 210, 255, 128, 255, 0, 0, 0, 238, 200], 
    "G": [229, 255, 255, 42, 192, 0, 180, 165, 128, 0, 0, 128, 0, 130, 162],  
    "B": [180, 255, 0, 42, 203, 128, 140, 0, 128, 0, 0, 0, 255, 238, 200] 
}
for color in unique_colors:
    print(color)
# # # Créer le DataFrame
colors_df = pd.DataFrame(data)

# # # Afficher le DataFrame
print(colors_df)

# # # ici je suis à la question 16
unique_colors_combin= pd.unique(champignons['Color'])
colors = pd.DataFrame(unique_colors_combin,columns=['Color'])
print(colors)


## 17
combinaison = colors['Color'].str.split('-', expand=True)
combinaison.columns = ['Color1', 'Color2']

for col in ['Color1', 'Color2']:
    combinaison = combinaison.merge(colors_df, left_on=col, right_on='Color', how='left', suffixes=('', '_'+col))

combinaison['R'] = combinaison[['R', 'R_Color2']].mean(axis=1)
combinaison['G'] = combinaison[['G', 'G_Color2']].mean(axis=1)
combinaison['B'] = combinaison[['B', 'B_Color2']].mean(axis=1)

combinaison.drop(['Color', 'R_Color2', 'G_Color2', 'B_Color2', 'Color1', 'Color2','Color_Color2'], axis=1, inplace=True)

colors = colors.join(combinaison, how='left')
print(colors)

# # # # Créer une copie du DataFrame original pour éviter de modifier les données d'origine
# champignons_copy = champignons.copy()

# Fusionner les données avec le DataFrame colors_df
champignons = pd.merge(champignons, colors_df, left_on='Color', right_on='Color', how='left')

# Remplacer les valeurs NaN dans les colonnes "R", "G" et "B" par -255
champignons[['R', 'G', 'B']] = champignons[['R', 'G', 'B']].fillna(-255)

# Supprimer la colonne "Color" qui est maintenant inutile
champignons.drop(columns=['Color'], inplace=True)

# Afficher les premières lignes du DataFrame pour vérification
print(champignons.head())

# 19
# Séparation des données en features (X) et la cible (y)
X = champignons.drop(columns=['Edible'])  # features
y = champignons['Edible']  # cible

# Séparation des données en un jeu d’entraînement et un jeu de test (75%/25%)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=42)

# Afficher les dimensions des jeux de données
print("Dimensions du jeu d'entraînement X :", X_train.shape)
print("Dimensions du jeu de test X :", X_test.shape)
print("Dimensions du jeu d'entraînement y :", y_train.shape)
print("Dimensions du jeu de test y :", y_test.shape)

# Initialiser le modèle SVC
svc_model = SVC()

# Entraîner le modèle sur le jeu d'entraînement
svc_model.fit(X_train, y_train)

# Faire des prédictions sur le jeu de test
y_pred = svc_model.predict(X_test)

# Calculer l'accuracy_score
accuracy = accuracy_score(y_test, y_pred)
print("Accuracy Score:", accuracy)

# Calculer la matrice de confusion
conf_matrix = confusion_matrix(y_test, y_pred)
print("Confusion Matrix:")
print(conf_matrix)

# Standardisation des données
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

# Entraînement du modèle SVC sur les données standardisées
svc_model2 = SVC()
svc_model2.fit(X_train_scaled, y_train)

# Prédiction sur les données de test
y_pred = svc_model2.predict(X_test_scaled)

# Évaluation de l'accuracy_score
accuracy = accuracy_score(y_test, y_pred)
print("Accuracy Score:", accuracy)

# Calcul de la confusion_matrix
conf_matrix2 = confusion_matrix(y_test, y_pred)
print("Confusion Matrix 2:")
print(conf_matrix2)


# Entraînement d'un arbre de décision avec une profondeur maximale de 3
dt = DecisionTreeClassifier(max_depth=3, random_state=42)
dt.fit(X_train, y_train)

# Prédiction sur le jeu de test
Y_pred_dt = dt.predict(X_test)

# Calcul et affichage de l'accuracy
accuracy_dt = accuracy_score(y_test, Y_pred_dt)
print("Accuracy du modèle d'arbre de décision: " , accuracy_dt)

conf_matrix3 = confusion_matrix(y_test, Y_pred_dt)
print("Confusion Matrix 3:")
print(conf_matrix3)



dot_data = export_graphviz(dt, out_file=None, 
                           feature_names=X_train.columns, 
                           filled=True, rounded=True,
                           special_characters=True)

# Génération de la représentation graphique
graph = graphviz.Source(dot_data)
print(graph)



#Question 24
joblib.dump(svc_model2, 'svm_model.txt')
joblib.dump(dt, 'decision_tree_model.txt')

#Partie 3.4

#Question 25


#      /   |    \
#     /    |     \
#    /     !      \

# la question 25 et 26 je ne sais pas si c'est des requettes 
# ou bien du code 
# je pense c'est sans code 
# tu me diras si tu supprimes ou pas



# URL de téléchargement de Node.js
nodejs_url = 'https://nodejs.org/dist/v16.14.0/node-v16.14.0-x64.msi'
# URL de téléchargement du framework Express
express_url = 'https://github.com/expressjs/express/archive/refs/tags/4.17.1.zip'

# Télécharger Node.js
nodejs_filename = './nodejs_installer.msi'
with open(nodejs_filename, 'wb') as f:
    f.write(requests.get(nodejs_url).content)
print("Node.js téléchargé avec succès.")

# Télécharger Express
express_zip_filename = './express.zip'
with open(express_zip_filename, 'wb') as f:
    f.write(requests.get(express_url).content)
print("Express téléchargé avec succès.")

# Extraire le fichier zip d'Express
with zipfile.ZipFile(express_zip_filename, 'r') as zip_ref:
    zip_ref.extractall('./')
print("Express extrait avec succès.")

# Supprimer le fichier zip d'Express après l'extraction
os.remove(express_zip_filename)
print("Fichier zip d'Express supprimé.")

print("Téléchargement et extraction terminés.")

#Question 26
# Contenu du fichier HTML
html_content = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
</head>
<body>
    <h2>Formulaire</h2>
    <form action="#" method="post">
        <label for="red">Rouge (R) :</label>
        <input type="text" id="red" name="red"><br><br>

        <label for="green">Vert (G) :</label>
        <input type="text" id="green" name="green"><br><br>

        <label for="blue">Bleu (B) :</label>
        <input type="text" id="blue" name="blue"><br><br>

        <label for="shape1">Shape 1 :</label>
        <input type="checkbox" id="shape1" name="shape1"><br>

        <label for="shape2">Shape 2 :</label>
        <input type="checkbox" id="shape2" name="shape2"><br>

        <label for="shape3">Shape 3 :</label>
        <input type="checkbox" id="shape3" name="shape3"><br><br>

        <label for="surface1">Surface 1 :</label>
        <input type="checkbox" id="surface1" name="surface1"><br>

        <label for="surface2">Surface 2 :</label>
        <input type="checkbox" id="surface2" name="surface2"><br>

        <label for="surface3">Surface 3 :</label>
        <input type="checkbox" id="surface3" name="surface3"><br><br>

        <label for="model">Choix du modèle :</label>
        <select id="model" name="model">
            <option value="svm">SVM</option>
            <option value="decision_tree">Arbre de décision</option>
        </select><br><br>

        <input type="submit" value="Soumettre">
    </form>
</body>
</html>
"""

# Écriture du contenu HTML dans un fichier
with open("formulaire.html", "w") as file:
    file.write(html_content)

print("Fichier HTML formulaire.html créé avec succès.")


#Question 27

# # # url_poisonous = "https://ultimate-mushroom.com/poisonous/103-abortiporus-biennis.html"
# # # url_edible = "https://ultimate-mushroom.com/edible/1010-agaricus-albolutescens.html"
# # # url_inedible = "https://ultimate-mushroom.com/inedible/452-byssonectria-terrestris.html"


# # # result_poisonous = comestible(url_poisonous)
# # # result_edible = comestible(url_edible)
# # # result_inedible = comestible(url_inedible)

# # # print("Poisonous:", result_poisonous)
# # # print("Edible:", result_edible)
# # # print("Inedible:", result_inedible)

# # # result_color_url1 = color(url_poisonous)
# # # result_color_url2=  color(url_edible)  
# # # result_color_url3 = color(url_inedible)
# # # print("Color:", result_color_url1)
# # # print("Color:", result_color_url2)
# # # print("Color:", result_color_url3)

# # # result_shape_url1 = shape(url_poisonous) 
# # # result_shape_url2 = shape(url_edible)
# # # result_shape_url3 = shape(url_inedible)
# # # print("Shape:", result_shape_url1)
# # # print("Shape:", result_shape_url2)
# # # print("Shape:", result_shape_url3)

# # # result_surface_url1 = surface(url_poisonous)
# # # result_surface_url2 = surface(url_edible)
# # # result_surface_url3 = surface(url_inedible)
# # # print("Surface:", result_surface_url1)
# # # print("Surface:", result_surface_url2)
# # # print("Surface:", result_surface_url3)

# # # result_csv_url1 = csv(url_poisonous)
# # # result_csv_url2 = csv(url_edible)
# # # result_csv_url3 = csv(url_inedible)
# # # print("csv:", result_csv_url1)
# # # print("csv:", result_csv_url2)
# # # print("csv:", result_csv_url3)

