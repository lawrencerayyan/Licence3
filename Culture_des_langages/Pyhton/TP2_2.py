import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin

def liens(adresse):
    
    response = requests.get(adresse)   
    
    soup = BeautifulSoup(response.text, 'html.parser')

    a_tags = soup.find('div', {'class': 'mw-parser-output'}).find_all('a', href=True)
    liens_set = {urljoin(adresse, a['href']) for a in a_tags}
    
    return liens_set


adresse_page = "https://iceandfire.fandom.com/wiki/Petyr_Baelish"
liens_set = liens(adresse_page)


print("Liens de la page vers d'autres pages du wiki:")
for lien in liens_set:
    print(lien)
   
