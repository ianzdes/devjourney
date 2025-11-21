package game.challenges;
import java.util.Scanner;

import game.Developer;

public class BossMessage extends Challenge {
    public BossMessage() {
        super("O chefe mandou mensagem perguntando o status no Slack!");
    }

    @Override 
    public void execute(Developer dev, Scanner sc) {
        // NÃO criamos new Scanner(System.in) aqui para não travar a Main
        System.out.println("O chefe pergunta: 'Qual a senha do servidor de prod?' (Dica: 1234)");
        System.out.print("Sua resposta: ");
        
        // Cuidado: nextLine pode pegar buffer vazio dependendo de como foi chamado antes
        String password = sc.nextLine(); 

        if("1234".equals(password)) {
            System.out.println("✅ zRespondeu rápido! +50XP");
            dev.gainXp(50);
        } else {
            System.out.println("❌ Errou a senha! O chefe ficou bravo. -30XP");
            dev.gainXp(-30);
        }
    }
}