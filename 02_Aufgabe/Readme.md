# Aufgabe 2: Kalah Board

In dieser Aufgabe wird der MiniMax Algorithmus implementiert. Dazu wird ein Kalah Board implementiert, welches die Spielregeln des Spiels Kalah beinhaltet. Das Spiel Kalah wird mit zwei Spielern gespielt, die abwechselnd Steine in die jeweiligen Kalahs legen. Ziel des Spiels ist es, mehr Steine als der Gegner in seinem Kalah zu haben. Das Spiel endet, wenn ein Spieler keine Steine mehr hat. Die Steine, die der Gegner noch hat, werden in den eigenen Kalah gelegt. Der Spieler mit den meisten Steinen gewinnt.

## Beispiel Bord

Zum testen der Algorithmen wurde ein Beispiel Bord bereitgestellt anhand welchem sich die Spielregeln gut testen lassen

             === Player A ===
          --- --- --- --- --- ---       
         | 5 | 4 | 3 | 2 | 1 | 0 |      
          --- --- --- --- --- ---       
         | 0 | 2 | 3 | 4 | 0 | 2 |      
          --- --- --- --- --- ---       
     ---                           ---  
    | A |                         | B | 
     ---                           ---  
    | 0 |                         | 0 | 
     ---                           ---  
          --- --- --- --- --- ---       
         | 1 | 0 | 1 | 3 | 2 | 1 |      
          --- --- --- --- --- ---       
         | 7 | 8 | 9 |10 |11 |12 |      
          --- --- --- --- --- ---       
             === Player B ===
             
Bei dem hier gezeigten Bord kann ein guter Algorithmus 12 Steine in den Kalah legen. Dies ist der optimale Spielzug wobei die KI aufgrund von Bonuszügen 9 Züge hintereinander durchführen kann.


## MiniMax Algorithmus

