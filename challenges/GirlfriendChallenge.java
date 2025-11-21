package game.challenges;

import java.util.InputMismatchException;
import java.util.Scanner;
import game.Developer;

public class GirlfriendChallenge extends Challenge { 
    
    public GirlfriendChallenge() {
        super("A sua namorada/o mandou uma mensagem");
    }

    @Override 
    public void execute(Developer dev, Scanner scanner) {
        System.out.println("MENSAGEM: Voce esta livre para sair hoje a noite?");
        System.out.println("1. Sim");
        System.out.println("2. Nao");
        System.out.println("3. Amanha");
        System.out.println("4. Terminar");
        System.out.print("Escolha: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.println("Resposta: Sim (+40 XP)");
                    dev.gainXp(40);
                    break;
                case 2:
                    System.out.println("Resposta: Nao (-20 XP)");
                    dev.gainXp(-20);
                    break;
                case 3:
                    System.out.println("Resposta: Amanha (0 XP)");
                    break;
                case 4:
                    System.out.println("Resposta: Terminar (-50 XP)");
                    dev.gainXp(-50);
                    break;
                default:
                    System.out.println("Invalido.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada invalida.");
            scanner.nextLine(); 
        }
    }
}