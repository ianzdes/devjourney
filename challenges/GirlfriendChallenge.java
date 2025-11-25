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
        System.out.println("MENSAGEM: Você está livre para sair hoje à noite?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.println("3. Amanhã");
        System.out.println("4. Terminar");
        System.out.print("Escolha: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.println("Resposta: Sim (+25 XP)");
                    dev.gainXp(40);
                    break;
                case 2:
                    System.out.println("A sua namorada não gostou da resposta! (-60 XP)");
                    dev.gainXp(-20);
                    break;
                case 3:
                    System.out.println("A sua namorada não gosta de esperar! (-30 XP)");
                    break;
                case 4:
                    System.out.println("Você partiu o coração da sua namorada! (-200 XP)");
                    dev.gainXp(-200);
                    break;
                default:
                    System.out.println("Inválido.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida.");
            scanner.nextLine(); 
        }
    }
}