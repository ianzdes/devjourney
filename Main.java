package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome do Dev? ");
        String devName = scanner.nextLine();

        Developer player = new Developer(devName);
        Career journey = new Career(player);

        journey.startJourney(); // O loop acontece lá dentro

        scanner.close(); // Fecha o scanner apenas no final de tudo
    }
}