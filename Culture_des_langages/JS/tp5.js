class Champignon {
    constructor(nom, poids, comestible) {
        this.nom = nom;
        this.poids = poids;
        this.comestible = comestible;
    }

    toString() {
        return `Champignon ${this.nom}, poids: ${this.poids}g, comestible: ${this.comestible}`;
    }
}

class Morille extends Champignon {
    constructor(nom, poids) {
        super(nom, poids, true);
    }
}

class Amanite extends Champignon {
    constructor(nom, poids, stries) {
        super(nom, poids, false);
        this.stries = stries;
    }

    toString() {
        return ` ${super.toString()}, stries: ${this.stries}`;
    }
}

class Golmotte extends Amanite {
    constructor(nom, poids) {
        super(nom, poids, false);
    }
}

class Panthere extends Amanite {
    constructor(nom, poids) {
        super(nom, poids, true);
    }
}

const morille = new Morille("Morille commune", 50);
const golmotte = new Golmotte("Amanite rougissante", 30);
const panthere = new Panthere("Amanite panthère", 40);

console.log(morille.toString());
console.log(golmotte.toString());
console.log(panthere.toString());


function selection(panier) {
    const panierSansToxique=[];
    for(let i = 0 ; i<panier.length ;i++){
        if(!panier[i].comestible){
            continue;
        }
        panierSansToxique.push(panier[i]);
    }
    return panierSansToxique ;
}

function selection2(panier) {
    const panierSansToxique2 = [];
    for (const champignon of panier ){
        if (champignon.comestible){
            panierSansToxique2.push(champignon);
        }
    } 
    return panierSansToxique2 ;
}

const selection3 = (panier) => panier.filter(champignon => champignon.comestible);

function danger_golmette(panier) {
    let poidDanger = 0 ;
    for(const champignon of panier){
        if(champignon instanceof Golmotte){
            poidDanger += champignon.poids; 
        } 
    }
    return poidDanger > 1000
}

function danger_golmottes(panier) {
    const poidsTotalGolmottes = panier.reduce((total, champignon) => {
        if (champignon instanceof Golmotte) {
            return total + champignon.poids;
        }
        return total;
    }, 0);

    return poidsTotalGolmottes > 1000;
}
