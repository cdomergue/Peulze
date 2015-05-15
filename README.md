# Peulze

## Compilation et lancement

* Requis : Maven

### Compilation
Après avoir correctement installé maven, lancer la commande :
```
mvn clean assembly:assembly
```

### Lancement
Décompresser le fichier target/Peulze.zip quelque part et lancer start.bat pour Windows ou start.sh pour Linux.

## Touches
Le  jeu n'utilise que la souris. Le clic gauche dans la main permet de prendre une pièce et de la déposer, et le clique droit de pivoter la pièce.
Cliquer sur une pièce du plateau sans avoir désélectionner une autre pièce retire cette première du plateau.

##Fonctions
Vous pouvez sauvegarder le jeu à tout moment en cliquant sur le bouton Sauvegarder. Il vous suffit de le charger ensuite depuis le menu principal. Attention, une seule sauvegarde n'est possible.

##Bugs connus
la mauvaise rotation des pièces lors du chargement