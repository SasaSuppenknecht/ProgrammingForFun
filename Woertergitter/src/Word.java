public class Word {
    private final String WORD; // jedes Wort sollten ausschließlich in Großbuchstaben gespeichert werden und sollte auch nicht veränderbar sein, dafuer erstellt man dann neue Worte
    private Direction direction;
    private int x;
    private int y;

    public Word (String wort) throws IllegalArgumentException{ //Konstruktor: Wort ohne Loesung
        if (wort.length() > 4 && wort.length() < 16) {
            this.WORD = wort.toUpperCase().trim();
        }else{
            throw new IllegalArgumentException("Wortlaenge nicht zwischen 5 und 15!");
        }
        this.direction = null;
        this.x = -1;
        this.y = -1;
    }

    public Word (String wort, Direction direction, int x, int y) throws IllegalArgumentException{ //Konstruktor: Wort mit Loesung
        if (wort.length() > 4 && wort.length() < 16) {
            this.WORD = wort.toUpperCase().trim();
        }else{
            throw new IllegalArgumentException("Wortlaenge nicht zwischen 5 und 15!");
        }
        this.direction = direction;
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
        return WORD;
    }

    public void getPositionInField(){ //Ausgabe in Konsole
        System.out.println(" "); //TODO
    }

    public void setSolution (Direction direction, int x, int y) throws IllegalArgumentException{ //eventuell mit nested class
        this.direction = direction;
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
