import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class remembers both a {@link Field} and a list of {@link Word}s in order to work with them. This class directly
 * communicates with the {@link UserInterface} and executes its task or delegates them to {@link Field} or {@link Word}.
 * It can also use these two components to "solve" the puzzle and create new ones based on a list.
 */

@SuppressWarnings("JavadocReference")
public class WordSearch {

    // Attributes ---------------------------------------------------------------------------------

    private Field field;
    private LinkedList<Word> words;


    // Constructors -------------------------------------------------------------------------------

    /**
     * Creates a {@link WordSearch} with no {@link Field} and an empty {@link LinkedList} of {@link Word}s.
     */

    public WordSearch (){
        field = null;
        words = new LinkedList<>();
    }

    /**
     * Creates a {@link WordSearch} with a {@link Field} of given {@literal length} and {@literal height} and an empty {@link LinkedList} of {@link Word}s.
     *
     * @param length length of the Field
     * @param height height of the Field
     */

    public WordSearch (int length, int height){
        field = new Field(length, height);
        words = new LinkedList<>();
    }


    // Methods ------------------------------------------------------------------------------------

    /**
     * Adds an instance of {@link Word} to the LinkedList {@link words}, but only if no other instance in words has the same value for
     * its String attribute {@link Word.WORD}. It also sorts the list by the length of each instances attribute {@link Word.WORD}.
     *
     * @param word the instance of {@link Word} that is to be inserted into {@link words}
     * @return {@literal true} if word is successfully added, {@literal false} if word is already contained in the list
     */

    public boolean addToWordlist(Word word) { // sortiert eingefuegt, laengstes Wort kommt als erstes
        ListIterator<Word> listIterator = words.listIterator(0);
        while (listIterator.hasNext()) {
            Word curWord = listIterator.next();
            if (curWord.getWord().length() < word.getWord().length()) {
                //words.add(word);
                listIterator.add(word);
                return true;
            }else if (curWord.getWord().length() == word.getWord().length()){
                if (curWord.getWord().equals(word.getWord())){
                    return false;
                }
            }
        }
        words.addLast(word);
        return true;
    }

    /**
     * Removes an instance of {@link Word} from the LinkedList {@link words} at the given index. The indexation of the parameter position
     * begins at 1, but is converted inside the method to 0-indexation.
     *
     * @param position index of the word that is to be removed in 1-indexation
     * @throws IndexOutOfBoundsException when the given index is not within the list
     */

    public void removeFromWordlist(int position) throws IndexOutOfBoundsException {
        int index = position - 1;
        if (words.size() <= index ) {
            throw new IndexOutOfBoundsException("Position zu groß!");
        }else if (index < 0 ) {
            throw new IndexOutOfBoundsException("Position darf nicht kleiner als 1 sein!");
        }else {
            words.remove(index);
        }
    }

    /**
     * Creates a new instance of {@link LinkedList} inside {@link words}, thus removing the old list.
     */

    public void clearWordlist() {
        words = new LinkedList<Word>();
    }

    public void createField(int length, int height) throws IllegalArgumentException {
        if (words == null || words.size() == 0 ) {
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
                        if (field.getChar(x, y) == 0) {
                            int randomNum = (int) (Math.random() * 26) + 65;
                            char c = (char) randomNum;
                            field.setChar(x, y, c, false);
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
            for (int y = 0; y < field.getHeight(); y++) { //TODO Fehler bei y = 2, Methode schreibt bei 5,2 4,3 obwohl sie nicht sollte
                for (int x = 0; x < field.getLength(); x++) {
                    LinkedList<Word> wordsSave = (LinkedList<Word>) words.clone();
                    Field fSave = field.cloneField();
                    Word w = words.get(index);
                    if (setWord(w, x, y, Direction.LEFT, fSave)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.LEFT, index +1 , wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UPLEFT, fSave)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.UPLEFT, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UP, fSave)) {
                        //this.print(false);
                        if (setSolutionAndRekCall(w, x, y, Direction.UP, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.UPRIGHT, fSave)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.UPRIGHT, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.RIGHT, fSave)) {
                        //this.print(false);
                        if (setSolutionAndRekCall(w, x, y, Direction.RIGHT, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWNRIGHT, fSave)) {
                        //this.print(false);
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWNRIGHT, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWN, fSave)) {
                        //this.print(false);
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWN, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }
                    if (setWord(w, x, y, Direction.DOWNLEFT, fSave)) {
                        if (setSolutionAndRekCall(w, x, y, Direction.DOWNLEFT, index +1, wordsSave, fSave)) {
                            return true;
                        }
                    }

                }
            }
            return false;
        }
    }

    private boolean setSolutionAndRekCall(Word w, int x, int y, Direction direction, int indCur, LinkedList<Word> wordsSave, Field fSave){
        w.setSolution(direction, x, y);
        this.print(false);
        boolean geklappt= fill(indCur);
        this.print(false);
        if (geklappt){
            return true;
        }else{
            words = wordsSave;
            field = fSave;
        }
        return false;
    }


    private boolean setWord (Word word, int x, int y, Direction direction, Field fSave){
        // Setzt Wort wenn es Platz hat und es keine falsche Ueberschneidung mit anderen Woerten hat
        int length = word.getWord().length();
        try {
            for (int i = 0; i < length; i++){
                char setChar = field.getChar(x,y);
                char toSet = word.getWord().charAt(i);
                if (setChar == 0 || setChar == toSet){
                    field.setChar(x, y, toSet, false);
                } else {
                    field = fSave;
                    return false;
                }
                x += direction.getXChange();
                y += direction.getYChange();
            }
        }catch (IllegalArgumentException | IndexOutOfBoundsException e ){
            field = fSave;
            return false;
        }
        return true;
    }

    public void createMinimalField() {}


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
                        Direction dir = word.getDirection(); // can't know direction at this point todo
                        int xChange = dir.getXChange();
                        int yChange = dir.getYChange();
                        int otherX = x + xChange;
                        int otherY = y + yChange;
                        for (int i = 1; i < chars.length; i++) {
                            foundLetter = field.getChar(otherX, otherY);
                            if (!(foundLetter == chars[i])) {
                                break;
                            }
                            if (i == chars.length - 1) {
                                foundThis = true;
                                word.setSolution(dir, x, y);
                            }
                        }
                    }
                }
            }
            if (!foundThis) {
                foundAll = false;
            }
        }

        return foundAll;
    }

    /**
     * Prints out the array represented by {@link field} and the associated String values of {@link words} in the console.
     * If {@literal withSolution} is true, {@link Word.direction}, {@link Word.x} and {@link Word.y} are printed out behind
     * the corresponding word if they have values assigned to them.
     *
     * @param withSolution a boolean deciding whether a solution is to be printed out behind the corresponding word or not
     */

    public void print(boolean withSolution) {
        printField(field);

        System.out.println();
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
            System.out.println();
        }
    }

    /**
     * //todo
     */

    public boolean getPositionInField(int index) throws IndexOutOfBoundsException { //Ausgabe in Konsole
        if (index < 1 || index > words.size()) {
            throw new IndexOutOfBoundsException("Der Index liegt nicht innerhalb der Liste.");
        }

        Word word = words.get(index - 1);
        if (word.getDirection() == null) {
            return false;
        }

        String wordString = word.getWord();
        Field f = field.cloneField();
        int xPos = word.getX();
        int yPos = word.getY();
        int xChange = word.getDirection().getXChange();
        int yChange = word.getDirection().getYChange();

        for (int i = 0; i < wordString.length(); i++) {
            char c = Character.toLowerCase(wordString.charAt(i));
            try {
                f.setChar(xPos, yPos, c, true);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Das Wort liegt nicht innerhalb des Feldes.");
            }
            xPos += xChange;
            yPos += yChange;
        }
        printField(f);

        return true;
    }

    private void printField(Field f) {
        System.out.print(" ");
        for (int x = 1; x <= f.getLength(); x++){
            System.out.print(" " + x);
        }
        for (int y = 0; y < f.getHeight(); y++) { //gibt Feld aus
            System.out.println();
            System.out.print(y+1);
            for (int x = 0; x < f.getLength(); x++) {
                System.out.print(" " + f.getChar(x, y));
            }
        }

        System.out.println();
    }

    // Getters ------------------------------------------------------------------------------------

    public Field getField() {return field;}

    public LinkedList<Word> getWords() {return words;}

}






















