class Mopokens{
    private String type;
    private int level;
    private int xp;

    public Mopokens(String type, int level){
        this.type = type;
        this.level = level;
        this.xp = 0;
    }
    public String getType(){
        return type;
    }
    public int getLevel(){
        return level;
    }

    public int getXP(){
        return xp;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setLevel(int level){
        this.level = level;
    }   

    public void setXP(int XP){
        this.xp = XP;
    }
    @Override
    public String toString(){
        return type + "#" + level;
    }

}
