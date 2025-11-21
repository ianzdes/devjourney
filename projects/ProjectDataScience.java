package game.projects;

public class ProjectDataScience extends Project {
    public ProjectDataScience(String name, int difficulty) {
        super(name, difficulty, 15 + difficulty * 10);
    }

    @Override
    public int finishProject() {
        System.out.println("Treinando modelo...");
        System.out.println("Modelo gerou insights.");
        return xpReward;
    }
}