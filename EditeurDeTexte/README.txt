Afin de pouvoir tester le logiciel, il existe deux solutions : 
    - $src/tests/TestEditor.java contient un script automatisé de test qui va
    lancer l'éditeur faire des insertions et des déplacements de curseur comme si
    l'utilisateur utilisait de vraies commandes et afficher les résultats correspondants
    en utilisant la chaine du MVC standard.
    - $src/tests/LaunchEditor.java permet de lancer l'éditeur en mode normal et de 
    l'utiliser de manière standard en saisissant manuellement des commandes.
La première méthode est recommandée car évite de se documenter sur les commandes de 
l'éditeur.
Information : certains bugs peuvent être encore présent malgré notre phase de débuggage.