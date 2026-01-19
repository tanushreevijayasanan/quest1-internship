import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        
        try{
            String myLine = scanner.nextLine();
            String oppLine = scanner.nextLine();

            List<Mopokens> myTeam = Parser.parseMopokens(myLine);
            List<Mopokens> oppTeam = Parser.parseMopokens(oppLine);
            
            if(myTeam.size() != 5){
                throw new IllegalArgumentException("your team must have exactly 5 mopokens !!");
            }
            
            if (oppTeam.size() != 5) {
                throw new IllegalArgumentException("opponent team must have exactly 5 mopokens !!");
            }
            
            Breeder myBreeder = new Breeder();
            Breeder opponentBreeder = new Breeder();
            
            for (Mopokens m : myTeam) {
                myBreeder.addMopoken(m);
            }
            
            for (Mopokens m : oppTeam) {
                opponentBreeder.addMopoken(m);
            }
            
            List<Mopokens> winningArrangement = BattleStrategy.findWinningArrangement(myTeam, oppTeam);
            
            if (winningArrangement != null) {
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < winningArrangement.size(); i++) {
                    output.append(winningArrangement.get(i));
                    if (i < winningArrangement.size() - 1) {
                        output.append(";");
                    }
                }
                System.out.println(output.toString());
                int wins = BattleStrategy.simulateBattle(winningArrangement, oppTeam);
                System.out.println("you won " + wins + " out of 5 battles :)");
            } else {
                System.out.println("you can't win :(");
            }
        } finally {
            scanner.close();
        }
    }
}