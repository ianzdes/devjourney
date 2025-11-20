package game.projects;

public class ProjectDataScience extends Project {
    public ProjectDataScience(String name, int difficulty) {
        super(name, difficulty, 70 + (difficulty * 12));
    }

    @Override
    public int finishProject() {
        System.out.println("📊 Limpando dataset e treinando modelo...");
        // Simulação simples: sempre dá certo, mas XP varia pouco
        System.out.println("✅ Modelo gerou insights valiosos!");
        return xpReward;
    }
}