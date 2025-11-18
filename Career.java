package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import game.Promotion.Level;

public class Career {
    private Developer developer;
    private List<Project> availableProjects;
    private List<Challenge> availableChallenges;
    private Random randomGenerator;
    private Scanner scanner;
    private boolean isLeaving = false;

    public Career(Developer developer) {
        this.developer = developer;
        this.availableProjects = new ArrayList<>();
        this.availableChallenges = new ArrayList<>();
        this.randomGenerator = new Random();
        this.scanner = new Scanner(System.in);

        setupEvents();
    }

    private void setupEvents() {

        availableProjects.add(new ProjectSmartContract("Legado C++ 'Caixa Preta'", 7));
        availableChallenges.add(new BossMessageChallenge());
    }

    // APÓS o método setupEvents() na classe Career.java

    public void startJourney() { // MÉTODO PRINCIPAL
        System.out.println("--- BEM-VINDO(A) À JORNADA DO DEV ---");
        
        // ATENÇÃO: Scanner e Random devem ser usados no loop.
        // O seu loop está vazio: while (developer.getPosition() != Level.CEO) {}
        
        while (developer.getPosition() != Level.CEO && isLeaving == false) { // a gente pode usar !isLeaving, mas a explicacao é diferente
            
            System.out.println("\n-------------------------------------");
            developer.showStats(); 
            System.out.println("-------------------------------------");
            
            // --- MENU E LÓGICA DE INTERAÇÃO VÃO AQUI ---
            System.out.println("1. Work on a Project");
            System.out.println("2. Study (Gain Skill)");
            System.out.println("3. Attempt Promotion");
            System.out.println("4. Leave");
            System.out.print("Choose option (1-4): "); // Adicione mais opções se necessário

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        workOnProject(); // Novo nome
                        break;
                    case 2:
                        System.out.print("Which skill do you want to learn? ");
                        developer.study(scanner.nextLine());
                        break;
                    case 3:
                        checkPromotion(); // Novo nome, lança a exceção
                        break;
                    case 4:
                        System.out.println("leaving the journey. bye!");
                        this.isLeaving = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }

                if (randomGenerator.nextInt(100) < 30) { 
                    generateRandomChallenge(); // Novo nome
                }
                
            } catch (InsufficientXPException e) { // Seu nome de exceção está correto!
                System.err.println("❌ PROMOTION FAILED: " + e.getMessage());
            } catch (java.util.InputMismatchException e) {
                System.err.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
            }
        }
        
        System.out.println("\n--- GAME OVER: YOU ARE THE CEO! 👑 ---");
    }

    // MÉTODOS AUXILIARES:

    private void workOnProject() {
        Project project = availableProjects.get(randomGenerator.nextInt(availableProjects.size()));
        
        System.out.println("\n💼 Starting Project: " + project.getName() + " (Difficulty: " + project.getDifficulty() + ")");

        // Chama o método polimórfico (finishProject)
        int xpGained = project.finishProject();
        developer.gainXp(xpGained);
    }

    private void generateRandomChallenge() {
        System.out.println("\n🚨 SURPRISE CHALLENGE!");
        Challenge challenge = availableChallenges.get(randomGenerator.nextInt(availableChallenges.size()));
        
        System.out.println("Challenge: " + challenge.getDescription());
        
        // Chama o método polimórfico (execute)
        challenge.execute(developer); 
    }

    private void checkPromotion() throws InsufficientXPException {
        developer.getPromotion(); 
    }
}