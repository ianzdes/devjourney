package game;

import java.util.ArrayList;
import java.util.List;

import game.exceptions.InsufficientXPException;
import game.service.Promotion.Level;

public class Developer {
    private String name;
    private double xp;
    private List<String> skills;
    private Level currentPosition;
    private double xpMultiplier = 1.0;
    private int boostRemainingProjects = 0;

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

    // teste
    public double getXpMultiplier() { return xpMultiplier; }
    public int getBoostRemainingProjects() { return boostRemainingProjects; }

    public void activateXpBoost(double multiplier, int duration) {
        this.xpMultiplier = multiplier;
        this.boostRemainingProjects = duration;
        System.out.println("boost ativado: xp x" + multiplier + "pelos proximos " + duration + " projetos");        
    }

    public void useBoost() {
        if (boostRemainingProjects > 0) {
            boostRemainingProjects--;
            if (boostRemainingProjects == 0) {
                this.xpMultiplier = 1.0; // desativa o boost
                System.out.println("boost expirado");
            }
        }
    }

    public void gainXp(double amount) {
        this.xp = Math.max(0, this.xp + amount);
    }

    public void setCurrentPosition(Level newLevel) {
        this.currentPosition = newLevel;
    }

    public void resetXp() {
        this.xp = 0;
    }

    public boolean addSkill(String skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            return true;
        }
        return false;
    }
}