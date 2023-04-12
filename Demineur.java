import java.util.Scanner;

public class Demineur {
    public static boolean gameOver;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bienvenue dans le jeu du démineur !\n");

            System.out.print("Veuillez entrer la largeur du terrain de jeu : ");
            int width = scanner.nextInt();

            System.out.print("Veuillez entrer la hauteur du terrain de jeu : ");
            int height = scanner.nextInt();

            System.out.print("Veuillez entrer le nombre de bombes : ");
            int bombCount = scanner.nextInt();

            Grid grid = new Grid(width, height, bombCount);

            gameOver = false;
            while (!gameOver) {
                grid.printGrid();
                System.out.print("Veuillez entrer la colonne de la case à révéler (entre 1 et " + width + ") : ");
                int x = scanner.nextInt() - 1;

                System.out.print("Veuillez entrer la ligne de la case à révéler (entre 1 et " + height + ") : ");
                int y = scanner.nextInt() - 1;

                System.out.print("Veuillez entrer l'action à effectuer (reveal, flag, unflag) : ");
                String action = scanner.next();

                grid.play(x, y, action);
            }
        }
    }
}