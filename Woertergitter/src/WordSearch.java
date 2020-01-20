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
            if(!fill(0)){
                throw new IllegalArgumentException("Woerter passen nicht ins Feld!");
            }else{
                // leere Felder fuellen
                for (int y = 0; y < field.getHeight(); y++) {
                    for (int x = 0; x < field.getLength(); x++) {
                        if (field.getChar(x, y) == ' ') {
                            int randomNum = (int) (Math.random() * 26) + 65;
                            char c = (char) randomNum;
                            field.setChar(x, y, c);
                        }
                    }
                }
            }
        }
    }

    private boolean fill( int index){
        if (index  == words.size()){
            return true;
        } else {
            for (int y = 0; y < field.getHeight(); y++) {
                for (int x = 0; x < field.getLength(); x++) {
                    LinkedList<Word> wordsSave = (LinkedList<Word>) words.clone();
                    Word w = words.get(index);
                    if (setWord(w, x, y, Direction.LEFT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.LEFT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UPLEFT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.UPLEFT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UP)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.UP, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UPRIGHT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.UPRIGHT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.RIGHT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.RIGHT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWNRIGHT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWNRIGHT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWN)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWN, ++index, wordsSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWNLEFT)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWNLEFT, ++index, wordsSave)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private boolean setSolutionAndRekCall(Word w, int x, int y, Direction direction, int indCur, LinkedList<Word> wordsSave){
        w.setSolution(direction, x, y);
        boolean geklappt= fill(indCur);
        if (geklappt){
            return true;
        }else{
            words = wordsSave;
        }
        return false;
    }


    private boolean setWord (Word word, int x, int y, Direction direction){
        // Setzt Wort wenn es Platz hat und es keine falsche Ueberschneidung mit anderen Woerten hat
        int length = word.getWord().length();
        Field fSave = field.cloneField();
        try {
            for (int i = 0; i < length; i++){
                char setted = field.getChar(x,y);
                char toSet = word.getWord().charAt(i);
                if (setted == 0 || setted == toSet){     //Defaut Wert von char?
                    field.setChar(x, y, toSet);
                } else {
                    field = fSave;
                    return false;
                }
                x += direction.getXChange();
                y += direction.getYChange();
            }
        }catch (IllegalArgumentException e){
            field = fSave;
            return false;
        }
        return true;
    }

    public void createMinimalField() {}



    //public boolean setField() {return false;}  //macht Saver

    public boolean solve() {
        boolean foundAll = true;
        int length = field.getLength();
        int height = field.getHeight();
        for (Word word : words) {
            boolean foundThis = false;
            char[] chars = word.getWord().toCharArray();
            char firstLetter = chars[0];
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < height; y++) {
                    char foundLetter = field.getChar(x, y);
                    if (foundLetter == firstLetter) {
                        Direction dir = word.getDirection();
                        int xChange = dir.getXChange();
                        int yChange = dir.getYChange();
                        for (int i = 1; i < chars.length; i++) {

                        }
                    }
                }
            }
        }


        return foundAll;
    }


    public Field getField() {
        return field;
    }

    public LinkedList<Word> getWords() {
        return words;
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






















