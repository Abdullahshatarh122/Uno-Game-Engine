import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {

        System.out.println("Enter the number of players : ");
        Scanner scanner = new Scanner(System.in);
        int playerNum = scanner.nextInt();
        Game unoGame = new StandardVariation("Standard Uno" , playerNum);
        unoGame.play();
    }
}