import java.util.LinkedList;
import java.util.ListIterator;

public class WordSearch {

    private Field field;
    private LinkedList<Word> words;

    public WordSearch (){       //leerer Konstruktor, für Benutzeroberfläche
        field = null;
        words = new LinkedList<>();
    }

    public WordSearch (int length, int height){
        field = new Field(length, height);
        words = new LinkedList<>();
    }



    public void addToWordlist(Word word) { // sortiert eingefuegt, laengstes Wort kommt als erstes
        if (!words.contains(word)) {
           ListIterator<Word> listIterator = words.listIterator(0);
           while (listIterator.hasNext()) {
               Word curWord = listIterator.next();
               if (curWord.getWord().length() > word.getWord().length()) {
                   //words.add(word);
                   listIterator.add(word);
               }
           }
        }
    }

    public void removeFromWordlist(int position) throws IllegalArgumentException {
        int index = position -1;
        if (words.size() <= index ) {
            throw new IllegalArgumentException("Position zu groß!");
        }else if (index < 0 ) {
            throw new IllegalArgumentException("Position darf nicht kleiner als 1 sein!");
        }else {
            words.remove(index);
        }
    }

    public void clearWordlist() {
        words = null;
    }

    public void createField(int length, int height) throws IllegalArgumentException {
        if (length < 0 ) {
            throw new IllegalArgumentException("Laenge des Feldes darf nicht kleiner als 0 sein!");
        }else if (height < 0 ) {
            throw new IllegalArgumentException("Hoehe des Feldes darf nicht kleiner als 0 sein!");
        }else if (words == null || words.size() == 0 ) {
            throw new IllegalArgumentException("Keine Woerter in Woerterliste!");
        }else if (words.getFirst().getWord().length() > Integer.max(length,height)) {
            throw new IllegalArgumentException("Feld ist zu klein fuer laengstes Wort!");
        }else {
            field = new Field(length, height);
            fill(words);

        }
    }

    private void fill(LinkedList<Word> words){
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getLength(); x++) {
                if (fitsWord(words.getFirst(), x, y, Direction.LEFT)){

                }
            }
        }
    }

    private boolean fitsWord (Word word, int x, int y, Direction direction){
        return true;
    }

    public void createMinimalField() {}



    //public boolean setField() {return false;}  //macht Saver

    public boolean solve() {return false;}

    public Field getField() {
        return field;
    }

    public Word[] getWords() {
        return (Word[])  words.toArray();
        // eventuell auch ueber "return LinkedList" realisierbar
    }

    private void print(boolean withSolution) {
        System.out.println( "  ");
        for (int x = 1; x <= field.getLength(); x++){
            System.out.print(" " + x);
        }
        for (int y = 0; y < field.getHeight(); y++) { //gibt Feld aus
            System.out.println(y+1);
            for (int x = 0; x < field.getLength(); x++) {
                System.out.print(" " + field.getChar(x, y));
            }
        }

        for (int i = 0; i < words.size(); i++){ //gibt Woerteliste aus
            Word word = words.get(i);
            System.out.println(i+1 + ". " + word.getWord() );
            if (withSolution){
                System.out.print(" " + word.getDirection() + " " + word.getX() + " " + word.getY());
            }
        }
    }


}






















