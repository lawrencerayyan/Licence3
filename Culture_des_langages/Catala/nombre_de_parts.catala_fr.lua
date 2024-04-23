déclaration énumération SituationFamiliale: 
-- Veuve
-- Célé
-- marié


déclaration cahmp d'application NombreDePart:
entrée nombre_enfant_mineurs_à_charge contenu entier 
entrée situation_familiale contenu SituationFamiliale

interne nombre_enfants_à_charge entenu entier

# ceci est un commentaire
résultat nombre_de_part contenu décimal 

``` catala
champs d'application NombreDePart:
    définition nombre_de_part égal à 
        si nombre_enfants_à_charge =  0 alors 
            selon situation_familiale sous forme 
                -- Veuve : 1,0
                -- Célibataire : 1,0
                -- Marié : 2,0
            sinon 0,0
```


## article 196 
``` catala
champs d'application NombreDePart:
    définition nombre_enfants_à_charge égal nombre_enfant_mineurs_à_charge
    
```