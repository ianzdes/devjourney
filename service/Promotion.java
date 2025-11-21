package game.service;

public class Promotion {
    public enum Level {
        INTERN("Estagiario", 0),
        JUNIOR("Junior", 2000),
        PLENO("Pleno", 4000),
        SENIOR("Senior", 8000),
        CEO("CEO", 15000);

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
            return this; 
        }
    }
}