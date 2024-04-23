> inclusion : nombre_de_part.catla_fr

```catala
#tests

## Test n°1
déclaration champ d'application Test1: 
    résultat nombre_de_parts contenu NombreDePart

champs d'application Test1 : 
    définition nombre_de_parts égal à 
        résultat de NombreDePart avec {
            -- nombre_enfant_mineurs_à_charge: 0
            -- sitiuation_familiale :Veuve
        }

    assertion nombre_de_parts.nombre_de_parts = 1,0
```