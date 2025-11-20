package game;

public class Promotion {
    public enum Level {
        INTERN("Estagiário", 100), // Ajustei XP para teste
        JUNIOR("Júnior", 200),
        PLENO("Pleno", 400),
        SENIOR("Sênior", 800),
        CEO("CEO", 1500);

        private final String position;
        private final int requiredXp;

        Level(String position, int requiredXp) {
            this.position = position;
            this.requiredXp = requiredXp;
        }

        public String getPosition() { return position; }
        public int getRequiredXp() { return requiredXp; }

        public Level getNextLevel() {
            int nextIndex = this.ordinal() + 1;
            if (nextIndex < values().length) {
                return values()[nextIndex];
            }
            return this; // Já é CEO
        }
    }
}