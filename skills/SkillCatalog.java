package game.skills;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SkillCatalog {
    
    private static final Map<String, Double> SKILL_EFFECTS = new HashMap<>();

    static {
        SKILL_EFFECTS.put("Java Avancado", 5.0);
        SKILL_EFFECTS.put("Docker", 3.0);
        SKILL_EFFECTS.put("Kubernetes", 7.0);
        SKILL_EFFECTS.put("Clean Code", 2.0);
        SKILL_EFFECTS.put("AWS", 6.0);
        SKILL_EFFECTS.put("CI/CD", 4.0);
        SKILL_EFFECTS.put("SQL Tuning", 1.0);
    }

    public static List<String> getAllSkills() { 
        return Arrays.asList(SKILL_EFFECTS.keySet().toArray(new String[0]));
    }
    
    public static double getBoostEffect(String skillName) {
        return SKILL_EFFECTS.getOrDefault(skillName, 0.0);
    }
}