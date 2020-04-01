# HEI4 ITI : Paul Jourdan du Mazot le Rat de Magnitot, Thibault Perroquin, Henri Gerardin, Malo Reinert

# 1. Description
Mise en place d'une application web pour gérer une liste de destination pour les semestres universitaire à l'étranger. Chaque élève peut s'inscrire et choisir sa destination préférée et créer une liste de favoris. L'administrateur peut gérer la liste des destinations (en rajouter ou en supprimer) et la liste des élèves inscrits. Un Utilisateur lambda peut avoir accès à la liste des destinations et à la description des universités.

# 2. Mise en place de la base de donnée.

Pour mettre en place la base de donnée, il faut lancer votre MariaDB en local et créer une nouvelle base de données "mobiliti_bdd".
Puis vous pouvez copier, coller les codes SQL qui se trouve dans src/main/ressources pour créer les différentes tables. 
Vous pouvez ensuite rentrer vos paramètres d'identification dans le fichier "jdbc.properties" se trouvant dans src/main/ressources.
Votre basse de données est prête.
