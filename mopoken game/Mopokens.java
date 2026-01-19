class Mopokens{
    private String type;
    private int level;

    public Mopokens(String type, int level){
        this.type = type;
        this.level = level;
    }
    public String getType(){
        return type;
    }
    public int getLevel(){
        return level;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setLevel(int level){
        this.level = level;
    }   
    @Override
    public String toString(){
        return type + "#" + level;
    }
}
