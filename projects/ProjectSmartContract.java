package game.projects;
import java.util.Random;

public class ProjectSmartContract extends Project {
    public ProjectSmartContract(String name, int difficulty) {
        super(name, difficulty, 100 + (difficulty * 10));
    }

    @Override
    public int finishProject() {
        System.out.println("🛠️ Desenvolvendo contrato na Blockchain...");
        if (getDifficulty() >= 7 && new Random().nextInt(100) < 20) {
            System.out.println("⚠️ Bug crítico no Gas Fee! O cliente reclamou.");
            return xpReward - 40;
        }
        System.out.println("✅ Deploy realizado com sucesso!");
        return xpReward;
    }
}