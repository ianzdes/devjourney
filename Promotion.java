package game;

public class Promotion {

    public enum Level {
        INTERN("Estagiário", 0),
        JUNIOR("Júnior", 100),
        PLENO("Pleno", 300),
        SENIOR("Sênior", 800),
        CEO("CEO", 1500);

        private String position;
        private int requiredXp;

        Level(String position, int requiredXp) { // não é public Level por que o java cria automaticamente as instâncias enum
            this.position = position;
            this.requiredXp = requiredXp;
        }

        public String getPosition() { return position; }
        public int getRequiredXp() { return requiredXp; }

        public Level getNextLevel() {
            Level[] levels = Level.values();
            int currentOrdinal = this.ordinal();

            if (currentOrdinal < levels.length - 1) {
                return levels[currentOrdinal + 1];
            }
            return this;
        }
    }
}
