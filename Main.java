package game;

import java.util.Scanner;
import game.service.Promotion.Level; // Necessário para exibir informações de XP

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        showWelcomeMenu(scanner);

        scanner.close();
    }

    private static void showWelcomeMenu(Scanner scanner) {
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("\n=====================================");
            System.out.println("          JORNADA DO DEV             ");
            System.out.println("=====================================");
            System.out.println("1. Iniciar Jogo");
            System.out.println("2. Explicacao do Jogo");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Opção 1: Iniciar o jogo
                    startGame(scanner);
                    exitMenu = true; 
                    break;
                case "2":
                    // Opção 2: Explicação
                    showExplanation();
                    break;
                case "3":
                    // Opção 3: Sair
                    System.out.println("Ate a proxima! O Dev precisa descansar.");
                    exitMenu = true; 
                    break;
                default:
                    System.out.println("Opcao invalida. Digite 1, 2 ou 3.");
            }
        }
    }

    private static void startGame(Scanner scanner) {
        System.out.print("\nNome do Dev? ");
        String devName = scanner.nextLine();

        Developer player = new Developer(devName);
        Career journey = new Career(player, scanner);
        
        // Inicia o loop principal do jogo
        journey.startJourney(); 
    }

    private static void showExplanation() {
        System.out.println("\n-------------------------------------");
        System.out.println("         COMO JOGAR: JORNADA DO DEV  ");
        System.out.println("-------------------------------------");
        System.out.println("- **Objetivo**: Alcancar o cargo de CEO.");
        System.out.println("- **Turnos**: A cada turno, escolha uma acao (Trabalhar/Estudar/Promover).");
        System.out.println("- **XP e Promocao**:");
        System.out.println("  - Voce comeca como Estagiario (Intern) com 50 XP.");
        System.out.printf("  - Para ser promovido a Junior, voce precisa de %.0f XP.%n", (double)Level.JUNIOR.getRequiredXp());
        System.out.println("  - Ao ser promovido, sua XP e zerada.");
        System.out.println("- **Projetos**: Exigem varios turnos para serem concluidos.");
        System.out.println("- **Skills**: Aprender skills concede XP permanente (multiplicador).");
        System.out.println("- **Desafios**: Ha 30%% de chance de um Desafio Surpresa ocorrer a cada turno.");
        System.out.println("- **Boost High Risk**: Se voce terminar o relacionamento (desafio), ganhara um boost temporario de 4.0x XP por 5 projetos!");
        System.out.println("-------------------------------------");
    }
}