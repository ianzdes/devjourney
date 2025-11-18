package game;

public abstract class Challenge {
    private String description;

    public Challenge(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }

    // todo desafio precisa ter um método execute()
    // mas o comportamento será definido pelas subclasses
    public abstract void execute(Developer dev);
}
