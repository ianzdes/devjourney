package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import game.Promotion.Level;
import game.challenges.BossMessageChallenge;
import game.challenges.Challenge;
import game.exceptions.InsufficientXPException;
import game.projects.Project;
import game.projects.ProjectAI;
import game.projects.ProjectDataScience;
import game.projects.ProjectSmartContract;

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
        this.scanner = new Scanner(System.in); // Scanner único para a Career

        setupEvents();
    }

    private void setupEvents() {
        // Adicionando variedade de projetos
        availableProjects.add(new ProjectSmartContract("Legado C++ 'Caixa Preta'", 7));
        availableProjects.add(new ProjectDataScience("Análise de Churn", 5));
        availableProjects.add(new ProjectAI("Chatbot da Empresa", 8));
        
        // Desafios
        availableChallenges.add(new BossMessageChallenge());
    }

    public void startJourney() {
        System.out.println("--- BEM-VINDO(A) À JORNADA DO DEV ---");
        
        while (developer.getPosition() != Level.CEO && !isLeaving) {
            System.out.println("\n-------------------------------------");
            developer.showStats(); 
            System.out.println("-------------------------------------");
            
            System.out.println("1. Work on a Project");
            System.out.println("2. Study (Gain Skill)");
            System.out.println("3. Attempt Promotion");
            System.out.println("4. Leave");
            System.out.print("Choose option: ");

            try {
                if (!scanner.hasNextInt()) { // Proteção contra input sujo
                    scanner.nextLine();
                    continue;
                }
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                switch (choice) {
                    case 1: workOnProject(); break;
                    case 2: 
                        System.out.print("Qual skill deseja estudar? ");
                        developer.study(scanner.nextLine());
                        break;
                    case 3: checkPromotion(); break;
                    case 4: isLeaving = true; break;
                    default: System.out.println("Opção inválida.");
                }

                // Chance de desafio aleatório (30%)
                if (!isLeaving && randomGenerator.nextInt(100) < 30) { 
                    generateRandomChallenge();
                }
                
            } catch (InsufficientXPException e) {
                System.err.println("❌ FALHA NA PROMOÇÃO: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Erro inesperado: " + e.getMessage());
            }
        }
        
        if (developer.getPosition() == Level.CEO) {
            System.out.println("\n--- GAME OVER: VOCÊ VIROU CEO! 👑 ---");
        } else {
            System.out.println("\n--- Saiu do jogo. Até mais! ---");
        }
    }

    private void workOnProject() {
        if(availableProjects.isEmpty()) return;
        
        Project project = availableProjects.get(randomGenerator.nextInt(availableProjects.size()));
        System.out.println("\n💼 Iniciando: " + project.getName() + " (Dificuldade: " + project.getDifficulty() + ")");
        
        int xpGained = project.finishProject();
        developer.gainXp(xpGained);
    }

    private void generateRandomChallenge() {
        if(availableChallenges.isEmpty()) return;

        System.out.println("\n🚨 DESAFIO SURPRESA!");
        Challenge challenge = availableChallenges.get(randomGenerator.nextInt(availableChallenges.size()));
        System.out.println("Situação: " + challenge.getDescription());
        
        // Passamos o scanner da Career para o desafio não fechar o stream
        challenge.execute(developer, scanner); 
    }

    private void checkPromotion() throws InsufficientXPException {
        developer.getPromotion(); 
    }
}