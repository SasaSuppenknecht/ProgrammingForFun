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
     */

    public void addToWordlist(Word word) { // sortiert eingefuegt, laengstes Wort kommt als erstes
        if (!words.contains(word)) {    //todo überprüft nur, ob schon eine gleiche Instanz dieses Wortes darin ist, nicht, ob schon ein Word mit demselben String darin ist
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
        System.out.print(" ");

        for (int x = 1; x <= field.getLength(); x++){
            System.out.print(" " + x);
        }
        for (int y = 0; y < field.getHeight(); y++) { //gibt Feld aus
            System.out.println();
            System.out.print(y+1);
            for (int x = 0; x < field.getLength(); x++) {
                System.out.print(" " + field.getChar(x, y));
            }
        }
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


    // Getters ------------------------------------------------------------------------------------

    public Field getField() {return field;}

    public LinkedList<Word> getWords() {return words;}

}






















