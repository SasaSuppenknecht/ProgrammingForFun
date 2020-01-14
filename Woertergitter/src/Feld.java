public class Feld {
    private final char[][] FELD; // Feldgröße ist nicht veränderbar, aber der Inhalt

    public Feld (int length, int hight){
        FELD = new char[length][hight];
    }

    public void setChar(int x, int y){

    }

    public char getChar(int x, int y){
        return ' ';
    }

}
