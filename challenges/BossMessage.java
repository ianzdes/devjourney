package game.challenges;
import java.util.Scanner;
import java.util.Random;
import game.Developer;
import java.util.InputMismatchException; // Necessário para tratar a entrada

public class BossMessage extends Challenge {
    private Random randomGenerator;

    public BossMessage() {
        super("O chefe mandou mensagem perguntando o status no Slack!");
        this.randomGenerator = new Random();
    }

    @Override 
    public void execute(Developer dev, Scanner sc) {
        
        // 1. Gera dois números aleatórios entre 1 e 50, por exemplo
        int num1 = randomGenerator.nextInt(50) + 1;
        int num2 = randomGenerator.nextInt(50) + 1;
        
        // 2. Calcula a resposta correta
        int correctAnswer = num1 + num2;
        
        System.out.println("O chefe pergunta: 'Para provar que é você, qual é o resultado de...'");
        System.out.printf("👉 ** %d + %d ** ? (Tempo é dinheiro!)$%n", num1, num2);
        System.out.print("Sua resposta: ");
        
        try {
            // nextLine() é usado para evitar problemas com o buffer
            String rawInput = sc.nextLine().trim(); 
            
            // Tenta converter a resposta do jogador para inteiro
            int playerAnswer = Integer.parseInt(rawInput);
            
            if (playerAnswer == correctAnswer) {
                System.out.println("Respondeu corretamente! +50 XP");
                dev.gainXp(50);
            } else {
                System.out.printf("Errou a senha! A resposta correta era %d. O chefe ficou bravo. -30 XP%n", correctAnswer);
                dev.gainXp(-30);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Resposta inválida (não é um número)! O chefe ficou bravo. -30 XP");
            dev.gainXp(-30);
        }
    }
}