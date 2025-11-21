package game.projects;
import java.util.Random;

public class ProjectAI extends Project {
    public ProjectAI(String name, int difficulty) {
        super(name, difficulty, 15 + difficulty * 5); // Paga bem, mas difícil
    }

    @Override
    public int finishProject() {
        System.out.println("🤖 Treinando Rede Neural...");
        Random rnd = new Random();
        
        if (rnd.nextInt(100) < 10) {
            System.out.println("⚠️ A IA alucinou e xingou o usuário. Rollback!");
            return xpReward / 2; // Penalidade grande
        }
        
        System.out.println("✅ A IA passou no Teste de Turing (quase)!");
        return xpReward;
    }
}