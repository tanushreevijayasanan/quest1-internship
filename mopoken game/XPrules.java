public class XPrules {
    private static final int win_xp = 25;
    private static final int lose_xp = 25;
    private static final int levelup_xp = 100;

    public static void addWinXP(Mopokens winner){
        winner.setXP(winner.getXP() + win_xp);
        levelUp(winner);
    }

    public static void subtractLossXP(Mopokens loser){
        loser.setXP(loser.getXP() - lose_xp);
        loser.setXP(newXP);
    }

    private static void levelUp(Mopokens mopoken){
        while (mopoken.getXP() >= levelup_xp){
            mopoken.setLevel(mopoken.getLevel() + 1);
            mopoken.setXP(mopoken.getXP() - levelup_xp);
        }
    }
    
}
