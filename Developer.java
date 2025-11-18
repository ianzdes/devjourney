package game;

import java.util.ArrayList;
import java.util.List;
import game.Promotion.Level;

public class Developer {
    private String name;
    private double xp;
    private List<String> skills;
    private Level currentPosition;

    public Developer(String name) {
        this.name = name;
        this.xp = 0.0;
        this.skills = new ArrayList<>();
        this.currentPosition = Level.INTERN;
    }

    public String getName() { return name; }
    public double getXp() { return xp; }
    public List<String> getSkills() { return skills; }
    public Level getPosition() { return currentPosition; }

    public void setName(String name) { this.name = name; }
    public void setXp(double xp) { this.xp = xp; }

    public void study(String newSkill) {
        if (this.skills.contains(newSkill)) {
            System.out.println("voce ja tem essa skill pai");
            this.xp += 5;
        }
        else {
            this.addSkill(newSkill);
            this.xp += 15;
            System.out.println("voce aprendeu uma nova skill, parabens pai");
        }
    }

    public void addSkill(String skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    public void gainXp(double amount) {
        this.xp += amount;

        if (this.xp < 0) {
            this.xp = 0; // evitar que fique com xp negativo
        }
        System.out.println(name + " ganhou " + amount + " de xp! XP total: " + this.xp);
    }

    public void getPromotion() throws InsufficientXPException {
        Level nextLevel = this.currentPosition.getNextLevel();

        if (nextLevel == this.currentPosition) {
            System.out.println("voce ja é o ceo");
            return;
        }

        int requiredXp = nextLevel.getRequiredXp();

        if (this.xp >= requiredXp) {
            this.currentPosition = nextLevel; // sobe de cargo
            // redefine xp apos a promocao para simular o comeco de um novo nivel
            this.xp = 0;
            System.out.println("promocao!");
        }
        else {
            throw new InsufficientXPException(
            "XP insuficiente. Você precisa de " + requiredXp + 
            " XP para ser " + nextLevel.getPosition() + ". XP atual: " + this.xp
            );
        }
    }

    public void showStats() {
        System.out.println("Nome: " + name);
        System.out.println("XP: " + xp);
        System.out.println("Habilidades: " + skills);
    }
}
