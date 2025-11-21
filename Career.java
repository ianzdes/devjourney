package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.projects.*;
import game.challenges.*;
import game.exceptions.InsufficientXPException;
import game.service.PromotionService; // NOVO: Serviço de Promoção
import game.SkillCatalog;     // NOVO: Catálogo de Skills
import game.service.Promotion.Level;

import java.util.InputMismatchException;

public class Career {
    private Developer developer;
    private List<Project> availableProjects;
    private List<Challenge> availableChallenges;
    private Random randomGenerator;
    private Scanner scanner;
    private boolean isLeaving = false; // Flag para sair

    public Career(Developer developer, Scanner scanner) {
        this.developer = developer;
        this.availableProjects = new ArrayList<>();
        this.availableChallenges = new ArrayList<>();
        this.randomGenerator = new Random();
        this.scanner = scanner; // Agora usa o Scanner passado do Main
        setupEvents();
    }

    private void setupEvents() {
        // Popule os eventos aqui, ajustando a XP (balanceamento)
        availableProjects.add(new ProjectSmartContract("Legado C++ 'Caixa Preta'", 7));
        availableChallenges.add(new HRChecking()); // Garantir que HRMessageChallenge recebe Scanner
    }

    // --- MÉTODOS DE CONTROLE DO JOGO ---

    public void startJourney() {
        System.out.println("--- BEM-VINDO(A) À JORNADA DO DEV ---");

        while (developer.getPosition() != Level.CEO && isLeaving == false) {
            
            showStats(); // NOVO: Chamada para o método que exibe o status
            System.out.println("-------------------------------------");

            System.out.println("1. Work on a Project");
            System.out.println("2. Study (Gain Skill)");
            System.out.println("3. Attempt Promotion");
            System.out.println("4. Leave");
            System.out.print("Choose option (1-4): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        workOnProject();
                        break;
                    case 2:
                        studySkill(); // MÉTODO PARA VALIDAR E APRENDER SKILL
                        break;
                    case 3:
                        attemptPromotion(); // NOVO MÉTODO (Chama o PromotionService)
                        break;
                    case 4:
                        System.out.println("💔 Leaving the career journey. Goodbye!");
                        this.isLeaving = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }

                if (!isLeaving && randomGenerator.nextInt(100) < 30) {
                    generateRandomChallenge();
                }

            } catch (InsufficientXPException e) {
                System.err.println("❌ PROMOTION FAILED: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
            }
        }

        if (developer.getPosition() == Level.CEO) {
            System.out.println("\n--- GAME OVER: YOU ARE THE CEO! 👑 ---");
        }
    }

    // --- MÉTODOS DE SERVIÇO (MOVEMOS A LÓGICA DO DEVELOPER PARA CÁ) ---

    // LÓGICA DE PROMOÇÃO (CHAMA O SERVIÇO)
    private void attemptPromotion() throws InsufficientXPException {
        // Chama o método estático do serviço, que faz a checagem e lança a exceção se falhar.
        PromotionService.attemptPromotion(this.developer);
    }

    // LÓGICA DE ESTUDO E VALIDAÇÃO DE SKILLS (USANDO SkillCatalog)
    private void studySkill() {
        List<String> allSkills = SkillCatalog.getAllSkills();
        List<String> availableToLearn = new ArrayList<>();
        
        // 1. Prepara a lista de skills para exibição
        System.out.println("\n📚 SKILLS DISPONÍVEIS PARA ESTUDO:");
        int index = 1;
        for (String skill : allSkills) {
            if (!developer.getSkills().contains(skill)) {
                System.out.println(index + ". " + skill);
                availableToLearn.add(skill);
                index++;
            }
        }
        
        if (availableToLearn.isEmpty()) {
            System.out.println("Você já aprendeu todas as skills! Reforçando o conhecimento (+5 XP).");
            developer.gainXp(5);
            return;
        }

        System.out.print("Escolha o NÚMERO da skill para aprender: ");
        
        try {
            int choiceIndex = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choiceIndex > 0 && choiceIndex <= availableToLearn.size()) {
                String chosenSkill = availableToLearn.get(choiceIndex - 1);
                
                // 2. Adiciona a skill e determina a XP (Lógica de XP do estudo)
                if (developer.addSkill(chosenSkill)) { // addSkill agora retorna true/false
                    developer.gainXp(10); // Valor balanceado
                    System.out.println("Nova skill aprendida: " + chosenSkill + "! +10 XP.");
                }
                
            } else {
                System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.err.println("❌ Entrada inválida. Digite um número.");
            scanner.nextLine();
        }
    }


    // LÓGICA DE DESAFIO (PASSANDO O SCANNER)
    private void generateRandomChallenge() {
        System.out.println("\n🚨 SURPRISE CHALLENGE!");
        Challenge challenge = availableChallenges.get(randomGenerator.nextInt(availableChallenges.size()));
        
        System.out.println("Challenge: " + challenge.getDescription());
        
        // PASSA O SCANNER PARA O DESAFIO
        challenge.execute(developer, this.scanner); 
    }
    
    // EXIBIÇÃO DE ESTATÍSTICAS (O QUE ERA O Developer.showStats())
    private void showStats() {
        System.out.println("\n-------------------------------------");
        System.out.printf("DEV: %s | CARGO: %s | XP: %.1f%n", 
            developer.getName(), 
            developer.getPosition().getPosition(), 
            developer.getXp());
        
        String skillList = developer.getSkills().isEmpty() ? 
            "Nenhuma" : developer.getSkills().toString().replaceAll("[\\[\\]]", "");
            
        System.out.println("Skills: " + skillList);
    }
    
    // (workOnProject continua o mesmo)
// Dentro de Career.java

    private void workOnProject() {
        // 1. Checa se há projetos disponíveis
        if (availableProjects.isEmpty()) {
            System.out.println("\n🚫 Não há projetos disponíveis no momento. Tente estudar ou desafiar o status quo!");
            return;
        }
        
        // Simplificação: pega o primeiro projeto disponível (você pode melhorar a lógica de escolha depois)
        Project selectedProject = availableProjects.get(0); 

        System.out.println("\n💻 TRABALHANDO em: " + selectedProject.getName());
        
        // 2. Chama o método de conclusão do projeto, que retorna o XP
        int xpEarned = selectedProject.finishProject(); 
        
        // O XP pode ser negativo se houver bugs (ProjectSmartContract, ProjectAI)
        
        // 3. Aplica o ganho (ou perda) de XP ao Developer
        developer.gainXp(xpEarned); 

        if (xpEarned > 0) {
            System.out.println("✅ Projeto concluído com sucesso! Ganhou +" + xpEarned + " XP.");
        } else if (xpEarned < 0) {
            System.out.println("❌ Projeto falhou! Penalidade de " + xpEarned + " XP.");
        } else {
            System.out.println("⚠️ Projeto concluído, mas sem XP. Talvez a dificuldade fosse muito baixa.");
        }
        
        // 4. Remove o projeto da lista para simular conclusão (o Dev não repete o mesmo projeto)
        availableProjects.remove(0);
    }
}