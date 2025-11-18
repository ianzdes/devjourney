package game;

import java.util.Scanner;

public class Main {
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("nome do dev?");
        String devName = scanner.nextLine();

        // cria o desenvolvedor
        Developer player = new Developer(devName);

        // cria o motor do jogo
        Career journey = new Career(player);

        // inicia o jogo
        journey.startJourney();

        scanner.close();
    }
}
