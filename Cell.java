public class Cell {

    //PROPRIETES
    private boolean revealed;
    private boolean bomb;
    private int number;
    private boolean flagged;
    
    //CONSTRUCTEUR
    public Cell() {
        this.revealed = false;
        this.bomb = false;
        this.number = 0;
        this.flagged = false;
    }

    //GET SET
    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
