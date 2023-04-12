import java.util.Random;

class Grid
{
    // PROPRIETES
    private Cell[][] array;
    public boolean game;

    // CONSTRUCTEURS
    public Grid(int longueur, int largeur, int nbBombs) {
        this.array = new Cell[longueur][largeur];
        this.init(largeur, longueur, nbBombs);
    }

    public Grid() {
        this.array = new Cell[5][5];
        this.init(5,5,3);
    }

    //GET SET
    public Cell[][] getArray() {
        return this.array;
    }

    public void setArray(Cell[][] array) {
        this.array = array;
    
    }

    //METHODES
    public void init(int numRows, int numCols, int numBombs) {
        // Initialiser la grille avec toutes les cases vides
        this.array = new Cell[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.array[i][j] = new Cell();
            }
        }
        
        // Placer les bombes aléatoirement
        int bombsPlaced = 0;
        Random rand = new Random();
        while (bombsPlaced < numBombs) {
            int randRow = rand.nextInt(numRows);
            int randCol = rand.nextInt(numCols);
            Cell cellule = this.array[randRow][randCol];
            if (!cellule.isBomb()) {
                cellule.setBomb(true);
                bombsPlaced++;
            }
        }
        
        // Calculer les nombres pour chaque case
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Cell cellule = this.array[i][j];
                if (cellule.isBomb()) {
                    continue; // Passer à la case suivante si c'est une bombe
                }
                int numAdjacentBombs = 0;
                // Vérifier chaque case adjacente pour savoir combien de bombes elle a
                for (int row = i - 1; row <= i + 1; row++) {
                    for (int col = j - 1; col <= j + 1; col++) {
                        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
                            if (this.array[row][col].isBomb()) {
                                numAdjacentBombs++;
                            }
                        }
                    }
                }
                // Stocker le nombre de bombes adjacentes dans la case
                cellule.setNumber(numAdjacentBombs);
            }
        }
    }

    public void printGrid() {
        // Parcourir chaque case de la grille
        for (int i = 0; i < getArray().length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Cell cellule = array[i][j];
                // Afficher un caractère différent selon l'état de la case
                if (cellule.isRevealed()) {
                    if (cellule.isBomb()) {
                        System.out.print("X"); // Case révélée contenant une bombe
                    } else {
                        System.out.print(cellule.getNumber()); // Case révélée contenant un nombre
                    }
                } else if (cellule.isFlagged()) {
                    System.out.print("F"); // Case marquée d'un drapeau
                } else {
                    System.out.print("."); // Case non révélée ni marquée
                }
                System.out.print(" "); // Ajouter un espace entre chaque case pour plus de clarté
            }
            System.out.println(); // Aller à la ligne à la fin de chaque ligne de cases
        }
    }
    public void gameOver(){
        for (int i = 0; i < getArray().length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (!array[i][j].isRevealed()) {
                    array[i][j].setRevealed(true);
                }
            }
        }
        Demineur.gameOver = true;
        System.out.println("Game Over");
        printGrid();

    }

    public boolean checkWin() {
        for (int i = 0; i < getArray().length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Cell currentCase = array[i][j];
                if (!currentCase.isBomb() && !currentCase.isRevealed()) {
                    return false; // une case non-mine n'est pas révélée
                } else if (currentCase.isBomb() && !currentCase.isFlagged()) {
                    return false; // une mine n'est pas marquée
                }
            }
        }
        return true; // toutes les cases non-mines sont révélées et toutes les mines sont marquées
    }
    

    private void revealCell(int x, int y) {
        // Vérifier que les coordonnées sont valides
        if (x < 0 || x >= this.array.length || y < 0 || y >= this.array[x].length) {
            return;
        }
        
        Cell cellule = this.array[x][y];
    
        // Si la case est déjà révélée ou marquée d'un drapeau, ne rien faire
        if (cellule.isRevealed() || cellule.isFlagged()) {
            return;
        }
    
        // Révéler la case actuelle
        cellule.setRevealed(true);
    
        // Si la case actuelle contient une bombe, ne pas révéler récursivement ses cases adjacentes
        if (cellule.isBomb()) {
            return;
        }
    
        // Si la case actuelle n'a pas de voisins avec des bombes, révéler les cases adjacentes
        if (cellule.getNumber() == 0) {
            // Parcourir les cases adjacentes
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    // Vérifier que les coordonnées sont valides et ne pas révéler la case actuelle à nouveau
                    if (i != x || j != y) {
                        revealCell(i, j); // Appeler la méthode récursivement pour révéler les cases adjacentes
                    }
                }
            }
        }
    }
    

    public void play(int x, int y, String action) {
        Cell cellule = this.array[x][y];

        if (action.equals("reveal")) {
             if (cellule.isBomb()) {
                gameOver();
            } else {
                revealCell(x, y);
                if (checkWin()){
                    System.out.print("You won!");
                    gameOver();
                }
            } 
            revealCell(x, y);
        } else if (action.equals("flag")) {
            cellule.setFlagged(true);
        } else if (action.equals("unflag")) {
            cellule.setFlagged(false);
        }
    }


}