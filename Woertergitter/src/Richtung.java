public enum Richtung {

    OB("oben"),
    UN("unten"),
    RE("rechts"),
    LI("links"),
    OBRE("oben rechts"),
    OBLI("obenlinks"),
    UNRE("unten rechts"),
    UNLI("unten links");

    private String s;
    private Richtung(String s) {
        this.s = s;
    }

    public String getString() {
        return s;
    }
}
