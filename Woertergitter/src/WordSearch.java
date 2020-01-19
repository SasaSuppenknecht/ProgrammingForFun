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
           while (listIterator.hasNext()) { //todo words ist zu Beginn leer, deswegen wird while nie erreicht
               Word curWord = listIterator.next();
               if (curWord.getWord().length() > word.getWord().length()) {
                   words.add(word);
                   //listIterator.add(word);
                   return;
               }
           }
            words.add(word);
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

    private boolean fill(LinkedList<Word> words){
        boolean geklappt;
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getLength(); x++) {
                LinkedList<Word> wordsSave = (LinkedList<Word>) words.clone();
                if (setWord(words.getFirst(), x, y, Direction.LEFT)){
                    words.removeFirst();
                    geklappt= fill(words);
                    if (geklappt){
                        return true;
                    }else{
                        words = wordsSave;
                    }
                }
                if (setWord(words.getFirst(), x, y, Direction.LEFT)){
                    //TODO
                }
            }
        }
        return false;
    }

    private boolean setWord (Word word, int x, int y, Direction direction){
        //TODO Setzt Wort wenn es Platz hat und es keine falsche Ueberschneidung mit anderen Woerten hat
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

    public void print(boolean withSolution) {
        System.out.print(" ");
        for (int x = 1; x <= field.getLength(); x++){
            System.out.print(" " + x);
        }
        for (int y = 0; y < field.getHeight(); y++) { //gibt Feld aus
            System.out.println("");
            System.out.print(y+1);
            for (int x = 0; x < field.getLength(); x++) {
                System.out.print(" " + field.getChar(x, y));
            }
        }
        System.out.println("");
        for (int i = 0; i < words.size(); i++){ //gibt Woerteliste aus
            Word word = words.get(i);
            System.out.print(i+1 + ". " + word.getWord() );
            if (withSolution){
                Direction dir = word.getDirection();
                String direction = null;
                if (dir != null){
                    direction = dir.getString();
                    System.out.print(" - Richtung:" + direction + " x:" + word.getX() + " y:" + word.getY());
                }
            }
            System.out.println("");
        }
    }


}






















