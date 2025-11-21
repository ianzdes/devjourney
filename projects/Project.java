package game.projects;

public abstract class Project {
    private String name;
    private int difficulty;
    protected int xpReward;
    private int progress; 

    public Project(String name, int difficulty, int xpReward) {
        this.name = name;
        this.difficulty = difficulty;
        this.xpReward = xpReward;
        this.progress = 0; 
    }

    public int getDifficulty() { return difficulty; }
    public String getName() { return name; }
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }

    public abstract int finishProject();
}