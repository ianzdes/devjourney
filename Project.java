package game;

// a classe é abstrata porque ela define a estrutura, mas não a lógica de conclusão
public abstract class Project {
    private String name;
    private int difficulty;

    // protected permite que as classes filhas (webproject, aiproject) acessem
    // o valor base da recompensa diretamente para aplicar bonus/penalidades
    protected int xpReward;

    public Project(String name, int difficulty, int xpReward) {
        this.name = name;
        this.difficulty = difficulty;
        this.xpReward = xpReward;
    }

    public int getDifficulty() { return difficulty; }

    public abstract int finishProject();
}
