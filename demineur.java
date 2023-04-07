public class demineur
{ 
    public static void main(String[] args) {
        var grid = new Grid();
        grid.printGrid();
        grid.play(1, 1, "reveal");
        grid.printGrid();
    }
}
 