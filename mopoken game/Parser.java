import java.util.*;

public class Parser {
    public static List<Mopokens> parseMopokens(String input) {
        List<Mopokens> mopokensList = new ArrayList<>();
        Set<String> seenTypes = new HashSet<>();
        
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("can't be empty !!");
        }
        
        String[] entries = input.trim().split(";");
        
        for (String entry : entries){
            entry = entry.trim();
            
            if (!entry.contains("#")){
                throw new IllegalArgumentException("invalid format !!");
            }
            
            String[] parts = entry.split("#");
            
            if (parts.length != 2){
                throw new IllegalArgumentException("invalid format !!");
            }
            
            String type = parts[0].trim();
            String levelStr = parts[1].trim();

            if (type.isEmpty()){
                throw new IllegalArgumentException("type can't be empty !!");
            }
            if(seenTypes.contains(type)){
                throw new IllegalArgumentException("duplicate mopoken type " + type + ", no type repeats allowed !!");
            }

            int level;
            try {
                level = Integer.parseInt(levelStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("level must be an integer !!");
            }
            
            if (level <= 0){
                throw new IllegalArgumentException("level must be positive !!");
            }
            
            mopokensList.add(new Mopokens(type, level));
        }
        
        return mopokensList;
    }
    
    public static Breeder parseBreeder(String input) {
        Breeder breeder = new Breeder();
        List<Mopokens> mopokensList = parseMopokens(input);
        
        for (Mopokens mopoken : mopokensList) {
            breeder.addMopoken(mopoken);
        }
        
        return breeder;
    }
}