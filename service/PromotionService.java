// game/service/PromotionService.java
package game.service; // Note o novo pacote

import game.Developer;
import game.exceptions.InsufficientXPException;
import game.service.Promotion.Level;

public class PromotionService {

    // Método estático para ser chamado diretamente pela classe Career
    public static void attemptPromotion(Developer dev) throws InsufficientXPException {
        Level currentLevel = dev.getPosition();
        Level nextLevel = currentLevel.getNextLevel();

        if (nextLevel == currentLevel) {
            System.out.println("Parabéns! Você já é o CEO e está no topo da carreira.");
            return;
        }

        if (dev.getXp() >= nextLevel.getRequiredXp()) {
            
            // ✅ CORREÇÃO: Apenas promove e reseta se o XP for suficiente
            dev.setCurrentPosition(nextLevel);
            dev.resetXp(); 
            System.out.println("🚀 PROMOVIDO! Seu novo cargo é: " + nextLevel.getPosition() + ".");
            
        } else {
            // Lança a exceção, o estado do Dev (XP) não é alterado
            throw new InsufficientXPException(
                "Faltam " + (nextLevel.getRequiredXp() - dev.getXp()) + " XP para ser promovido a " + nextLevel.getPosition()
            );
        }
    }
}