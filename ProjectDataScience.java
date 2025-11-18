package game;

import java.util.Random;

public class ProjectDataScience extends Project{

    public ProjectDataScience(String name, int difficulty) {
        super(name, difficulty, 70 + (difficulty * 12));
    }

    @Override
    public int finishProject() {
        System.out.println("resto do codigo aqui");
    }
}
