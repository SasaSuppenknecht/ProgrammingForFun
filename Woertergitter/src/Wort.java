public class Wort {
    private final String WORT; // jedes Wort sollten ausschließlich in Großbuchstaben gespeichert werden und sollte auch nicht veränderbar sein, dafuer erstellt man dann neue Worte
    private Richtung richtung;
    private int x;
    private int y;

    public Wort (String wort){ //Konstruktor: Wort ohne Loesung
        this.WORT = wort;
        this.richtung = null;
        this.x = -1;
        this.y = -1;
    }

    public Wort (String wort, Richtung richtung, int x, int y) throws IllegalArgumentException{ //Konstruktor: Wort mit Loesung
        this.WORT = wort.toUpperCase().trim();
        this.richtung = richtung;
        if (x >= 0) {
            this.x = x;
        }else{
            throw new IllegalArgumentException("x wert kleiner 0");
        }
        if (y >= 0) {
            this.y = y;
        }else{
            throw new IllegalArgumentException("y wert kleiner 0");
        }
    }

    public String getWord(){
        return WORT;
    }

    public void getPositionInField(){ //Ausgabe in Konsole
        System.out.println(" "); //TODO
    }

    public void setSolution (Richtung richtung, int x, int y) throws IllegalArgumentException{ //eventuell mit nested class
        this.richtung = richtung;
        if (x >= 0) {
            this.x = x;
        }else{
            throw new IllegalArgumentException("x wert kleiner 0");
        }
        if (y >= 0) {
            this.y = y;
        }else{
            throw new IllegalArgumentException("y wert kleiner 0");
        }
    }

}
