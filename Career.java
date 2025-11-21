package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

import game.projects.*; 
import game.challenges.*; 
import game.exceptions.InsufficientXPException;
import game.service.PromotionService; 
import game.SkillCatalog;
import game.service.EasterEgg; 
import game.service.Promotion.Level;

public class Career {
    private Developer developer;
    private List<Project> availableProjects;
    private List<Challenge> availableChallenges;
    private Random randomGenerator;
    private Scanner scanner;
    private boolean isLeaving = false; 
    private Project activeProject = null; 

    public Career(Developer developer, Scanner scanner) {
        this.developer = developer;
        this.availableProjects = new ArrayList<>();
        this.availableChallenges = new ArrayList<>();
        this.randomGenerator = new Random();
        this.scanner = scanner; 
        setupEvents();
    }

    private void setupEvents() {
        availableProjects.add(new ProjectSmartContract("Site Institucional Basico", 3));
        availableChallenges.add(new HRChecking()); 
        availableChallenges.add(new GirlfriendChallenge()); 
    }
    
    private void generateNewProjects() {
        System.out.println("\nNovos projetos chegaram");
        availableProjects.add(new ProjectSmartContract("Refatoracao Legacy", 7));
        availableProjects.add(new ProjectAI("Feature de Recomendacao", 6));
        availableProjects.add(new ProjectDataScience("Pipeline de Dados", 5));
    }

    public void startJourney() {
        System.out.println("--- JORNADA DO DEV ---");

        while (developer.getPosition() != Level.CEO && isLeaving == false) {
            
            showStats(); 
            System.out.println("-------------------------------------");
            System.out.println("1. Trabalhar em Projeto");
            System.out.println("2. Estudar (Ganhar Skill)");
            System.out.println("3. Tentar Promocao");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            String rawInput = scanner.nextLine(); 

            if (EasterEgg.activate(developer, rawInput)) { 
                continue; 
            }

            try {
                int choice = Integer.parseInt(rawInput.trim()); 

                switch (choice) {
                    case 1:
                        workOnProject();
                        break;
                    case 2:
                        studySkill(); 
                        break;
                    case 3:
                        attemptPromotion(); 
                        break;
                    case 4:
                        System.out.println("Saindo do jogo.");
                        this.isLeaving = true;
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }

                if (!isLeaving && randomGenerator.nextInt(100) < 30) {
                    generateRandomChallenge(); 
                }

            } catch (InsufficientXPException e) {
                System.err.println("PROMOCAO FALHOU: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Entrada invalida. Digite um numero.");
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }

        if (developer.getPosition() == Level.CEO) {
            System.out.println("\n--- GAME OVER: VOCE E O CEO! ---");
        }
    }
    
    private void workOnProject() {
        if (activeProject == null) {
            if (availableProjects.isEmpty()) {
                generateNewProjects(); 
                if (availableProjects.isEmpty()) {
                    System.out.println("Nao ha projetos disponiveis.");
                    return;
                }
            }
            
            activeProject = availableProjects.remove(0); 
            System.out.println("Iniciando projeto: " + activeProject.getName());
            System.out.println("Progresso: 0%");
            return; 
        }
        
        int progressIncrease = 10;
        int currentProgress = activeProject.getProgress();
        int newProgress = currentProgress + progressIncrease;
        
        activeProject.setProgress(newProgress);
        
        System.out.println("Trabalhando em " + activeProject.getName() + "...");

        if (newProgress >= 100) {
            int baseXpEarned = activeProject.finishProject(); 
            
            double multiplier = developer.getXpMultiplier();
            int finalXpEarned = (int) (baseXpEarned * multiplier);

            if (multiplier > 1.0) {
                System.out.printf("BOOST ATIVO Base XP: %d -> Final XP: %d%n", baseXpEarned, finalXpEarned);
                developer.useBoost(); 
            }
            
            developer.gainXp(finalXpEarned); 
            System.out.println("PROJETO CONCLUIDO");
            System.out.println("Ganhou " + finalXpEarned + " XP.");
            activeProject = null; 
            
        } else {
            System.out.println("Progresso: " + newProgress + "%");
            developer.gainXp(5); 
        }
    }

    private void attemptPromotion() throws InsufficientXPException {
        PromotionService.attemptPromotion(this.developer);
    }
    
    private void generateRandomChallenge() {
        if (availableChallenges.isEmpty()) return;
        
        System.out.println("\nDESAFIO SURPRESA");
        Challenge challenge = availableChallenges.get(randomGenerator.nextInt(availableChallenges.size()));
        System.out.println(challenge.getDescription());
        challenge.execute(developer, this.scanner); 
    }
    
    private void studySkill() {
        List<String> allSkills = SkillCatalog.getAllSkills();
        List<String> availableToLearn = new ArrayList<>();
        
        System.out.println("SKILLS DISPONIVEIS:");
        int index = 1;
        for (String skill : allSkills) {
            if (!developer.getSkills().contains(skill)) {
                System.out.println(index + ". " + skill);
                availableToLearn.add(skill);
                index++;
            }
        }
        
        if (availableToLearn.isEmpty()) {
            System.out.println("Todas as skills aprendidas. +5 XP.");
            developer.gainXp(5);
            return;
        }

        System.out.print("Escolha o numero: ");
        try {
            int choiceIndex = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choiceIndex > 0 && choiceIndex <= availableToLearn.size()) {
                String chosenSkill = availableToLearn.get(choiceIndex - 1);
                if (developer.addSkill(chosenSkill)) {
                    developer.gainXp(10); 
                    System.out.println("Skill aprendida: " + chosenSkill + " (+10 XP)");
                }
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada invalida.");
            scanner.nextLine();
        }
    }

    private void showStats() {
        System.out.println("\n-------------------------------------");
        System.out.printf("DEV: %s | CARGO: %s | XP: %.1f", 
            developer.getName(), 
            developer.getPosition().getPosition(), 
            developer.getXp());
            
        if (developer.getBoostRemainingProjects() > 0) {
            System.out.printf(" | BOOST: x%.1f (%d restantes)", 
                developer.getXpMultiplier(), 
                developer.getBoostRemainingProjects());
        }
        System.out.println(); 
        System.out.println("Skills: " + developer.getSkills());
    }
}