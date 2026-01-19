import java.util.*;
public class TypeAdvantage {
    private static final Map<String, List<String>> advantageMap = new HashMap<>();
    static {
        advantageMap.put("Fire", Arrays.asList("Grass", "Ghost"));
        advantageMap.put("Water", Arrays.asList("Fire"));
        advantageMap.put("Grass", Arrays.asList("Electric", "Fighting"));
        advantageMap.put("Electric", Arrays.asList("Water"));
        advantageMap.put("Psychic", Arrays.asList("Ghost"));
        advantageMap.put("Fighting", Arrays.asList("Electric"));
        advantageMap.put("Ghost", Arrays.asList("Fighting", "Fire", "Electric"));
    }
    public static boolean hasTypeAdvantage(String attacker, String defender) {
        List<String> advantages = advantageMap.get(attacker);
        if (advantages == null) {
            return false;
        }
        return advantages.contains(defender);
    }
}
