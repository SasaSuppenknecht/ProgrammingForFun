import java.util.LinkedList;

public class Woertersuche {

    private Feld feld;
    private LinkedList<Wort> woerter;


    public void printWordlist() {}

    public void addToWordlist(Wort wort) {}

    public boolean removeFromWordlist(int index) {return false;}

    public void clearWordlist() {}

    public boolean createField(int length, int height) {return false;}

    public void createMinimalField() {}

    public Feld getFeld() {
        return feld;
    }

    public Wort[] getWoerter() {
        return (Wort[]) woerter.toArray();
        // eventuell auch ueber "return LinkedList" realisierbar
    }

    public boolean setField() {return false;}  //still needs file-parameter

    public boolean solve() {return false;}

    private void printSolution() {}
}
