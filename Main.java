package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome do Dev? ");
        String devName = scanner.nextLine();

        Developer player = new Developer(devName);
        
        // CORREÇÃO AQUI: Agora você passa o 'player' E o 'scanner'
        Career journey = new Career(player, scanner);

        journey.startJourney(); 

        scanner.close();
    }
}