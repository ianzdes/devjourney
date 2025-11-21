package game.projects;
import java.util.Random;

public class ProjectSmartContract extends Project {
    public ProjectSmartContract(String name, int difficulty) {
        super(name, difficulty, 10 + difficulty * 10); 
    }

    @Override
    public int finishProject() {
        System.out.println("Desenvolvendo contrato...");
        if (getDifficulty() >= 7 && new Random().nextInt(100) < 20) {
            System.out.println("Bug critico encontrado.");
            return 5; 
        }
        System.out.println("Deploy realizado.");
        return xpReward;
    }
}