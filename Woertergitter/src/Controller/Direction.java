package Controller;

public enum Direction {

    UP("oben", 0, -1),
    DOWN("unten", 0, 1),
    RIGHT("rechts", 1, 0),
    LEFT("links", -1,0),
    UPRIGHT("oben rechts", 1, -1),
    UPLEFT("obenlinks", -1,-1),
    DOWNRIGHT("unten rechts", 1,1),
    DOWNLEFT("unten links", -1,1);


    // Attributes ---------------------------------------------------------------------------------

    private String s;
    private int xChange;
    private int yChange;


    // Constructors -------------------------------------------------------------------------------

    Direction(String s, int xChange, int yChange) {
        this.s = s;
        this.xChange = xChange;
        this.yChange = yChange;
    }


    // Getters ------------------------------------------------------------------------------------

    public String getString() {
        return s;
    }

    public int getXChange() {return xChange;}

    public int getYChange() {return yChange;}
}
