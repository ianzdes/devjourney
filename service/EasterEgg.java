package game.service;
import game.Developer;

public class EasterEgg {
    private static final String SECRET_CODE = "sudo"; 
    private static final int BONUS_XP = 500;

    public static boolean activate(Developer dev, String input) {
        if (input != null && input.trim().equalsIgnoreCase(SECRET_CODE)) {
            dev.gainXp(BONUS_XP);
            System.out.println("ROOT ACESSADO");
            System.out.println("Bonus de " + BONUS_XP + " XP.");
            return true;
        }
        return false;
    }
}