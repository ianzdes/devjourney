package game.challenges;
import java.util.Scanner;
import game.Developer;

public class HRChecking extends Challenge {
    public HRChecking() {
        super("O RH ");
    }

    @Override 
    public void execute(Developer dev, Scanner scanner) {
        System.out.println("Digite sua senha: (dica: 1234)");
        String password = scanner.nextLine(); 

        if("1234".equals(password)) {
            System.out.println("Rapido +25XP");
            dev.gainXp(25); 
        } else {
            System.out.println("Lento -10XP");
            dev.gainXp(-10); 
        }
    }
}