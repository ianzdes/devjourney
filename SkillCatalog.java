package game;

import java.util.Arrays;
import java.util.List;

public class SkillCatalog {

    private static final List<String> ALL_SKILLS = Arrays.asList(
        "skill 1",
        "skill 2",
        "skill 3",
        "skill 4",
        "skill 5",
        "skill 6",
        "skill 7"
    );

    public static List<String> getAllSkills() { return ALL_SKILLS; }
}
