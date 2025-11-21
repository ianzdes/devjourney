package game.service; 

import game.Developer;
import game.exceptions.InsufficientXPException;
import game.service.Promotion.Level;

public class PromotionService {
    public static void attemptPromotion(Developer dev) throws InsufficientXPException {
        Level currentLevel = dev.getPosition();
        Level nextLevel = currentLevel.getNextLevel();

        if (nextLevel == currentLevel) {
            System.out.println("Voce ja e o CEO.");
            return;
        }

        if (dev.getXp() >= nextLevel.getRequiredXp()) {
            dev.setCurrentPosition(nextLevel);
            dev.resetXp(); 
            System.out.println("PROMOVIDO PARA " + nextLevel.getPosition());
        } else {
            throw new InsufficientXPException(
                "Faltam " + (nextLevel.getRequiredXp() - dev.getXp()) + " XP para " + nextLevel.getPosition()
            );
        }
    }
}