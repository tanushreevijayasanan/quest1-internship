import java.util.*;
public class Breeder {
    private static final int MAX_MOPOKENS = 5;
    private List<Mopokens> mopokensList;
    public Breeder() {
        mopokensList = new ArrayList<>();
    }
    public void addMopoken(Mopokens mopoken) {
        if (mopokensList.size() >= MAX_MOPOKENS) {
            throw new IllegalStateException("you can't have more than " + MAX_MOPOKENS + " mopokens :(");
        }
        if (hasType(mopoken.getType())) {
            throw new IllegalArgumentException("you already have a mopoken of type " + mopoken.getType() + ":(");
        }
        mopokensList.add(mopoken);
    }
    public boolean hasType(String type) {
        for (Mopokens mopoken : mopokensList) {
            if (mopoken.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
    public List<Mopokens> getMopokens() {
        return new ArrayList<>(mopokensList);
    }
    public int size() {
        return mopokensList.size();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < mopokensList.size(); i++) {
            sb.append(mopokensList.get(i));
            if (i < mopokensList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}