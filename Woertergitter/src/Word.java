public class Word {

    // Attributes ---------------------------------------------------------------------------------

    private final String WORD; // jedes Wort sollten ausschließlich in Großbuchstaben gespeichert werden und sollte auch nicht veränderbar sein, dafuer erstellt man dann neue Worte
    private Direction direction;
    private int x;
    private int y;


    // Constructors -------------------------------------------------------------------------------

    /**
     * Creates a new instance of class Word and sets its attributes. The word-String is transformed to uppercase and lose
     * white spaces at the front and end are cut off. It creates a Word without a solution.
     *
     * @param word the input word as a String
     * @throws IllegalArgumentException when the 'word'-parameter does not only contain the 26 letters of the alphabet or if
     * the length of the input-string is not between 5 and 15
     */

    public Word (String word) throws IllegalArgumentException{ //Konstruktor: Wort ohne Loesung
        wordTester(word);

        this.WORD = word.toUpperCase().trim();;
        this.direction = null;
        this.x = -1;
        this.y = -1;
    }

    /**
     * Creates a new instance of class Word and sets its attributes. The word-String is transformed to uppercase and lose
     * white spaces at the front and end are cut off. It creates a Word with a solution.
     *
     * @param word the input word as a String
     * @param direction the direction in the Field saved as enumeration Direction
     * @param x x-position in Field
     * @param y y-position in Field
     * @throws IllegalArgumentException when the 'word'-parameter does not only contain the 26 letters of the alphabet or if
     * the length of the input-string is not between 5 and 15
     */

    public Word (String word, Direction direction, int x, int y) throws IllegalArgumentException{ //Konstruktor: Wort mit Loesung
        wordTester(word);

        this.WORD = word.toUpperCase().trim();;
        this.direction = direction;

        if (x >= 0) {
            this.x = x;
        }else{
            throw new IllegalArgumentException("x-Wert darf nicht kleiner als 0 sein.");
        }
        if (y >= 0) {
            this.y = y;
        }else{
            throw new IllegalArgumentException("y-Wert darf nicht kleiner als 0 sein.");
        }
    }

    /**
     * Helper method for the constructors. Tests if the word only contains the 26 letters of the alphabet and if its length
     * is between 5 and 15. Otherwise it throws an IllegalArgumentException.
     *
     * @param word the input word as a String
     * @throws IllegalArgumentException when the 'word'-parameter does not only contain the 26 letters of the alphabet or if
     * the length of the input-string is not between 5 and 15
     */

    private void wordTester(String word) throws IllegalArgumentException {
        char[] chars = word.toCharArray();
        for (char c: chars) {
            if (c < 65 || c > 122 || (c > 90 && c < 97)) {
                throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden.");
            }
        }

        if (word.length() < 5 || word.length() > 15) {
            throw new IllegalArgumentException("Wortlaenge muss zwischen 5 und 15 sein!");
        }
    }

    // Methods ------------------------------------------------------------------------------------

    /**
     * //todo
     */

    public void getPositionInField(){ //Ausgabe in Konsole
        System.out.println(" "); //TODO
    }

    /**
     * Sets the solution of a Word. The solution is given as a coordinate (x/y) in the Field and a direction.
     *
     * @param direction the direction in the Field saved as enumeration Direction
     * @param x x-position in Field
     * @param y y-position in Field
     * @throws IllegalArgumentException when x or y are smaller than 0 and thus outside of the Field
     */

    public void setSolution (Direction direction, int x, int y) throws IllegalArgumentException {
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


    // Getters ------------------------------------------------------------------------------------

    public String getWord() {return WORD;}

    public int getX() {return x;}

    public int getY() {return y;}

    public Direction getDirection() {return direction;}
}
