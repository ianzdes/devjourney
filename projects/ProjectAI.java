package game.projects;
import java.util.Random;

public class ProjectAI extends Project {
    public ProjectAI(String name, int difficulty) {
        super(name, difficulty, 15 + difficulty * 5); 
    }

    @Override
    public int finishProject() {
        System.out.println("Treinando Rede Neural...");
        Random rnd = new Random();
        if (rnd.nextInt(100) < 10) {
            System.out.println("Erro na IA. Rollback.");
            return xpReward / 2; 
        }
        System.out.println("Sucesso no treino.");
        return xpReward;
    }
}