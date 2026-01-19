import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleStrategy {
    
    private static final int needed_wins = 3;
    private static final int total_wins = 5;
    
    public static List<Mopokens> findWinningArrangement(List<Mopokens> myTeam, List<Mopokens> opponentTeam) {
        if (myTeam.size() != total_wins || opponentTeam.size() != total_wins) {
            throw new IllegalArgumentException("Both teams must have exactly 5 mopokens");
        }
        List<List<Mopokens>> allArrangements = generatePermutations(myTeam);
        
        List<Mopokens> bestArrangement = null;
        int maxWins = 0;

        for (List<Mopokens> arrangement : allArrangements) {
            int wins = simulateBattle(arrangement, opponentTeam);
            
            if (wins > maxWins) {
                maxWins = wins;
                bestArrangement = arrangement;
            }
        }
        
        if (maxWins >= needed_wins) {
            return bestArrangement;
        }
        
        return null;
    }

    public static int simulateBattle(List<Mopokens> myArrangement, List<Mopokens> opponentTeam) {
        int wins = 0;
        
        for (int i = 0; i < total_wins; i++) {
            Mopokens myMopokens = myArrangement.get(i);
            Mopokens opponentMopokens = opponentTeam.get(i);
            
            if (BattleOfMopokens.canBeat(myMopokens, opponentMopokens)) {
                wins++;
            }
        }
        return wins;
    }
    
    public static int executeBattle(List<Mopokens> myArrangement, List<Mopokens> opponentTeam){
        int wins = 0;
        int loss = 0;
        int draws = 0;
        for (int i =0; i < total_wins; i++){
            Mopokens myMopokens = myArrangement.get(i);
            Mopokens oppMopokens = opponentTeam.get(i);
            Mopokens winner = BattleOfMopokens.whoIsTheWinner(myMopokens, oppMopokens);
            if (winner == null){
                draws++;
            }else if (winner == myMopokens){
                wins++;
                XPrules.addWinXP(myMopokens);
                XPrules.subtractLossXP(oppMopokens);
            }else{
                loss++;
                XPrules.addWinXP(oppMopokens);
                XPrules.subtractLossXP(myMopokens);
            }

        }
        return new int[]{wins, loss, draws};
    }

    public static List<List<Mopokens>> generatePermutations(List<Mopokens> Mopokenss) {
        List<List<Mopokens>> result = new ArrayList<>();
        List<Mopokens> copy = new ArrayList<>(Mopokenss);
        permute(copy, 0, result);
        return result;
    }
    
    private static void permute(List<Mopokens> list, int start, List<List<Mopokens>> result) {
        if (start == list.size() - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permute(list, start + 1, result);
            Collections.swap(list, start, i);
        }
    }
}