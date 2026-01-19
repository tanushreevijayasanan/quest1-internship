public class BattleOfMopokens {
    public static boolean canBeat(Mopokens attacker, Mopokens defender) {
        boolean attackerAdv = TypeAdvantage.hasTypeAdvantage(attacker.getType(), defender.getType());
        boolean defenderAdv = TypeAdvantage.hasTypeAdvantage(defender.getType(), attacker.getType());

        if (attackerAdv){
            if (defender.getLevel() >= (2* attacker.getLevel())){
                return false;
            }
            return true;
        }
        if (defenderAdv){
            if (attacker.getLevel() >= (2* defender.getLevel())){
                return true;
            }
            return false;
        }
        return attacker.getLevel() > defender.getLevel();
    }
    public static Mopokens whoIsTheWinner(Mopokens mopoken1, Mopokens mopoken2) {
        if (canBeat(mopoken1, mopoken2)) {
            return mopoken1;
        } else {
            return mopoken2;
        }
    }
}
