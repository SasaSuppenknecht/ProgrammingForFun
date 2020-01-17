public enum Direction {

    UP("oben"),
    DOWN("unten"),
    RIGHT("rechts"),
    LEFT("links"),
    UPRIGHT("oben rechts"),
    UPLEFT("obenlinks"),
    DOWNRIGHT("unten rechts"),
    DOWNLEFT("unten links");

    private String s;
    private Direction(String s) {
        this.s = s;
    }

    public String getString() {
        return s;
    }
}
