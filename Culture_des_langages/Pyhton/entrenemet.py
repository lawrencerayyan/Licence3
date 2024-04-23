def miroir(chaine):
    miroir_chaine = ""
    for i in range(len(chaine)-1 , -1,-1) :
        miroir_chaine += chaine[i]
    return miroir_chaine

chain_originale = "Bonjour"
chaine_resultat = miroir(chain_originale)

print("chaine resultante: ", chaine_resultat)

def entrelacement(l1, l2):
    l3 = []
    