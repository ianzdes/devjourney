package game.projects;

public abstract class Project {
    private String name;
    private int difficulty;
    protected int xpReward;

    public Project(String name, int difficulty, int xpReward) {
        this.name = name;
        this.difficulty = difficulty;
        this.xpReward = xpReward;
    }

    public int getDifficulty() { return difficulty; }
    public String getName() { return name; }

    public abstract int finishProject();
}