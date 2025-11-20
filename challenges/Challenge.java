package game.challenges;
import java.util.Scanner;

import game.Developer;

public abstract class Challenge {
    private String description;

    public Challenge(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }

    // Adicionado Scanner sc como parâmetro
    public abstract void execute(Developer dev, Scanner sc);
}