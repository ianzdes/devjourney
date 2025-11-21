// game/service/EasterEggService.java
package game.service;

import game.Developer;

public class EasterEgg {

    // A palavra-chave secreta que o usuário deve digitar
    private static final String SECRET_CODE = "sudo"; // Um código fácil de testar
    private static final int BONUS_XP = 500;

    /**
     * Tenta ativar o Easter Egg. 
     * @param dev O desenvolvedor que receberá o bônus.
     * @param input A string digitada pelo usuário.
     * @return true se o Easter Egg foi ativado, false caso contrário.
     */
    public static boolean activate(Developer dev, String input) {
        
        // Remove espaços extras e verifica se a entrada corresponde ao código secreto
        if (input != null && input.trim().equalsIgnoreCase(SECRET_CODE)) {
            
            dev.gainXp(BONUS_XP);
            
            System.out.println("\n");
            System.out.println("-------------------------------------");
            System.out.println("✨ Você obteve privilégios de root! ✨");
            System.out.println("    Comando '" + SECRET_CODE + "' executado com sucesso.");
            System.out.println("    Bônus de +" + BONUS_XP + " XP concedido.");
            System.out.println("-------------------------------------");
            System.out.println("\n");
            
            return true;
        }
        return false;
    }
}