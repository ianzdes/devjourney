package game.challenges;

import java.util.Scanner;

import game.Developer;

public class HRChecking extends Challenge{
        public HRChecking() {
        super("Mensagem urgente do chefe!");
    }

// Em HRMessageChallenge.java
    @Override 
    public void execute(Developer dev, Scanner scanner) { // Recebe o 'scanner'
        // 🛑 REMOVA esta linha:
        // Scanner sc = new Scanner(System.in); 
        
        System.out.println("Digite sua senha: ");
        // ✅ Use o 'scanner' que foi passado
        String password = scanner.nextLine(); 
        
        // ... (restante da lógica de XP, com valores balanceados, ex: +25/-10)
        if("1234".equals(password)) {
            System.out.println("Respondeu rápido! +25XP");
            dev.gainXp(25); // Valor balanceado
        }
        else {
            System.out.println("Demorou muito! -10XP");
            dev.gainXp(-10); // Valor balanceado
        }
    }
}
