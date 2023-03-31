import java.util.EnumMap;

class Grid
{
    
}

class Mine
{

}

class Flag
{

}

public class demineur
{ 

    public enum Direction {
        N,NE,E,SE,S,SW,W,NW
    };

    EnumMap<Direction, Integer> neighboursCell = new EnumMap<>(Direction.class);

    public static void main(String[] args) {
    System.out.println("oui");
    }

/*  public void iniGrid(Grid){
    * initialise grille
    }

    public void save(Path){  
    * Enregistre dans un fichier l'état de la grille
    }

    public void load(Path){
    * Charge depuis un fichier l'état de la grille
    }
*/
}


/* Idée classe et fonction
 * regarde classe en diagonale/verticale/horizontale
 * met/enlève drapeau
 * calcul nombre de mine autour 8 max
 * si 0 mine -> découvre jusqu'à trouver une mine en ligne et colonne
 * ini: random mines cases cachés/vide
 */

 