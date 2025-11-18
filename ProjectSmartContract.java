package game;

import java.util.Random;

public class ProjectSmartContract extends Project{
    
    public ProjectSmartContract(String name, int difficulty) {
        super(name, difficulty, 100 + (difficulty * 10));
    }

    @Override
    public int finishProject() {
        System.out.println("cripto");

        if (getDifficulty() >= 8 && new Random().nextInt(100) < 15) {
            System.out.println("furo de seguranca na implantacao");

            return xpReward - 40;
        }

        System.out.println("deu tudo certo");
        return xpReward;
    }
}
