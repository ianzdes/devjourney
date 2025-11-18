package game;

import java.util.Scanner;

public class BossMessageChallenge extends Challenge {
    public BossMessageChallenge() {
        super("Mensagem urgente do chefe!");
    }

    @Override 
    public void execute(Developer dev) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite sua senha: ");
        String password = sc.nextLine();

        if("1234".equals(password)) {
            System.out.println("Respondeu rápido! +50XP");
            dev.gainXp(50);
        }
        else {
            System.out.println("Demorou muito! -30XP");
            dev.gainXp(-30);
        }
    }
}
