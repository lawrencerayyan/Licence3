from bs4 import BeautifulSoup
kl = open ( "King.html","r")
klsoup = BeautifulSoup ( kl , "html.parser" )

def titre(soup) :
    return soup.title.string

resultat = titre(klsoup)
print("Titre de la page:", resultat)

def afficher_h2(soup) :
    for h2 in soup.find_all('h2') :
        print(h2.string)
        
afficher_h2(klsoup)

def nb_par_avec_lien(soup) :
    cmpt = 0 
    count_p = soup.find_all('p')
    for nb_a  in count_p :
        if nb_a.find('a'):
            cmpt +=1
    return cmpt
print(nb_par_avec_lien(klsoup))
