public class Wort {
    private final String WORT; // jedes Wort sollten ausschließlich in Großbuchstaben gespeichert werden und sollte auch nicht veränderbar sein, dafür erstellt man dann neue Worte
    private Richtung richtung;
    private int x;
    private int y;

    public Wort (String wort){ //Konstruktor: Wort ohne Loesung
        WORT = wort;
    }

    public Wort (String wort, String richtung, int x, int y){ //Konstruktor: Wort mit Loesung
        WORT = wort;
    }

    public String getWord(){
        return "";
    }

    public void getPositionInField(){ //Ausgabe in Konsole

    }

    public void setSolution (String richtung, int x, int y){ //eventuell mit nested class

    }

}
