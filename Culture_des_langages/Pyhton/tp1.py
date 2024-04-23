def miroir(chaine):
    chaine_miroir = ""
    for i in range(len(chaine) -1, -1, -1):
        chaine_miroir += chaine[i]
    #for caractere in reversed(chaine):
     #   chaine_miroir += caractere
    return chaine_miroir

chaine_originale = "Bonjour"
resultat_miroir = miroir(chaine_originale)

print("Cha�ne originale :", chaine_originale)
print("Cha�ne miroir    :", resultat_miroir)

def entrelacement(l1, l2):
    resultat = []
    min_len = min(len(l1), len(l2))

    for i in range(min_len):
        resultat.append(l1[i])
        resultat.append(l2[i])

    
    resultat.extend(l1[min_len:])
    resultat.extend(l2[min_len:])

    return resultat

liste1 = [1, 2, 3,4]
liste2 = ['a', 'b', 'c', 'd', 'e']
resultat_entrelacement = entrelacement(liste1, liste2)
print("Liste 1         :", liste1)
print("Liste 2         :", liste2)
print("Entrelacement   :", resultat_entrelacement)

class FileVide(Exception):
    pass

class File:
    def __init__(self):
        self.elements = []

    def ajouter(self, e):
        self.elements.append(e)

    def retirer(self):
        if not self.elements:
            raise FileVide("La file est vide.")
        
        premier_element = self.elements[0]
        del self.elements[0]
        return premier_element

def vider(file):
    while True:
        try:
            element = file.retirer()
            print("�l�ment retir� :", element)
        except FileVide:
            # La file est vide, on quitte la boucle
            break

# Exemple d'utilisation
ma_file = File()

ma_file.ajouter(1)
ma_file.ajouter(2)
ma_file.ajouter(3)

print("Contenu initial de la file :")
vider(ma_file)

    

ma_file = File()

ma_file.ajouter(1)
ma_file.ajouter(2)
ma_file.ajouter(3)

try:
    print("�l�ment retir� :", ma_file.retirer())
    print("�l�ment retir� :", ma_file.retirer())
    print("�l�ment retir� :", ma_file.retirer())
    print("�l�ment retir� :", ma_file.retirer()) 
except FileVide as e:
    print(f"Exception : {e}")

class Miroir:
    def __init__(self, chaine):
        self.chaine = chaine

    def __iter__(self):
        return iter(reversed(self.chaine))


chaine_originale = "Bonjour"
miroir_iterable = Miroir(chaine_originale)
m = Miroir ( " bar " )
for c in m :
    print ( c )
