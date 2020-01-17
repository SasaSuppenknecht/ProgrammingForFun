import java.util.LinkedList;

public class WordSearch {

    private Field field;
    private LinkedList<Word> words;

    public WordSearch (){} //leerer Konstruktor, für Benutzeroberfläche

    public WordSearch (int length, int height){
        field = new Field(length, height);
    }

    public void printWordlist() {}

    public void addToWordlist(Word word) {}

    public boolean removeFromWordlist(int index) {return false;}

    public void clearWordlist() {}

    public boolean createField(int length, int height) {return false;}

    public void createMinimalField() {}

    public Field getField() {
        return field;
    }

    public Word[] getWords() {
        return (Word[])  words.toArray();
        // eventuell auch ueber "return LinkedList" realisierbar
    }

    public boolean setField() {return false;}  //still needs file-parameter

    public boolean solve() {return false;}

    private void printSolution() {}
}
