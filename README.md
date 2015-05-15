# Peulze

## Compilation et lancement

* Requis : Maven

### Compilation
Après avoir correctement installé maven, lancer la commande :
```
clean package assembly:directory
```

### Lancement
Se rendre dans le dossier *target/Peulze-jar-with-dependencies* puis lancer `java glpoo.esiea.peulze.game.TheGame`
Sous Windows, il suffit de faire *SHIFT + CLIQUE DROIT* puis de cliquer sur *Ouvrir une fenêtre de commande ici*.

## Touches
Le  jeu n'utilise que la souris. Le clic gauche dans la main permet de prendre une pièce et de la déposer, et le clique droit de pivoter la pièce.
Cliquer sur une pièce du plateau sans avoir désélectionner une autre pièce retire cette première du plateau.

##Fonctions
Vous pouvez sauvegarder le jeu à tout moment en cliquant sur le bouton Sauvegarder. Il vous suffit de le charger ensuite depuis le menu principal. Attention, une seule sauvegarde n'est possible.

##Bugs connus
En cours de résolution : le non validation de certaines pièces valides; la sauvegarde semi-fonctionnelle