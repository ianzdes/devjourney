package game;

import java.util.ArrayList;
import java.util.List;
import game.Promotion.Level;
import game.exceptions.InsufficientXPException;

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

    public Level getPosition() { return currentPosition; }

    public void study(String newSkill) {
        if (skills.contains(newSkill)) {
            System.out.println("Você já domina " + newSkill + ". Ganhou +5 XP por reforço.");
            gainXp(5);
        } else {
            skills.add(newSkill);
            System.out.println("Nova skill aprendida: " + newSkill + "! +15 XP.");
            gainXp(15);
        }
    }

    public void gainXp(double amount) {
        this.xp = Math.max(0, this.xp + amount); // Garante que não fica negativo
        // System.out.println(name + " agora tem " + this.xp + " XP."); // Opcional, para não poluir
    }

    public void getPromotion() throws InsufficientXPException {
        Level nextLevel = currentPosition.getNextLevel();

        if (nextLevel == currentPosition) {
            System.out.println("Você já está no topo!");
            return;
        }

        if (this.xp >= nextLevel.getRequiredXp()) {
            this.currentPosition = nextLevel;
            this.xp = 0; // Reseta XP para o novo nível
            System.out.println("🎉 PROMOVIDO PARA " + currentPosition.getPosition() + "!");
        } else {
            throw new InsufficientXPException(
                "Faltam " + (nextLevel.getRequiredXp() - this.xp) + " XP para " + nextLevel.getPosition()
            );
        }
    }

    public void showStats() {
        System.out.printf("DEV: %s | CARGO: %s | XP: %.1f%n", name, currentPosition.getPosition(), xp);
        System.out.println("Skills: " + (skills.isEmpty() ? "Nenhuma" : skills.toString()));
    }
}