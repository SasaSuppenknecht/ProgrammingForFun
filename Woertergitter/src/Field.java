public class Field {

    // Attributes ---------------------------------------------------------------------------------

    private final char[][] FIELD; // Feldgröße ist nicht veränderbar, aber der Inhalt
    private final int LENGTH, HEIGHT;


    // Constructors -------------------------------------------------------------------------------

    /**
     * Creates an instance of Field of given length and height. The coordinate-system representing this field starts with
     * (0/0) in the top-left corner.
     *
     * @param length length of the Field
     * @param height height of the Field
     * @throws IllegalArgumentException when either the given length or height are below 0
     */

    public Field (int length, int height) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("length darf nicht kleiner als 1 sein");
        } else if (height < 0) {
            throw new IllegalArgumentException("height darf nicht kleiner als 1 sein");
        }
        this.FIELD = new char[length][height];
        this.LENGTH = length;
        this.HEIGHT = height;
    }


    // Methods ------------------------------------------------------------------------------------

    /**
     * Places a character in the given position (x/y) inside the char-array FIELD. Only the 26 letters of the alphabet are
     * accepted as characters and saved as uppercase characters. The method throws an IllegalArgumentException if other
     * characters outside of the given scope are placed in the FIELD.
     *
     * @param x x-position in FIELD
     * @param y y-position in FIELD
     * @param c the character that is to be inserted
     * @throws IllegalArgumentException when c is not a letter of the alphabet
     * @throws IndexOutOfBoundsException when x is below 0 or equal to or greater than LENGTH or when y is below 0 or
     * equal to or greater than HEIGHT
     */

    public void setChar(int x, int y, char c) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (x < 0 || x >= LENGTH) {
            throw new IndexOutOfBoundsException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= HEIGHT) {
            throw new IndexOutOfBoundsException("y liegt außerhalb des Feldes");
        } else if (c < 65 || c > 122 || (c < 97 && c > 90)) {
            throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden");
        }

        if (c >= 97) {
            c = Character.toUpperCase(c);
        }
        FIELD[x][y] = c;
    }

    /**
     * Creates and returns
     *  //todo
     * @return
     */

    public Field cloneField(){
        char[][] f = FIELD.clone();
        return new Field(f.length, f[0].length); //todo soll dies nur ein gleich groeßes Feld oder eine genaue Kopie von FIELD zurückgeben?
    }


    // Getters ------------------------------------------------------------------------------------

    /**
     * Returns the respective character at the given position, but throws an IndexOutOfBoundsException when x or y are not
     * within FIELD.
     *
     * @param x x-position in FIELD
     * @param y y-position in FIELD
     * @return the character at the given position
     * @throws IndexOutOfBoundsException when x is below 0 or equal to or greater than LENGTH or when y is below 0 or
     * equal to or greater than HEIGHT
     */

    public char getChar(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= LENGTH) {
            throw new IndexOutOfBoundsException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= HEIGHT) {
            throw new IndexOutOfBoundsException("y liegt außerhalb des Feldes");
        }
        return FIELD[x][y];
    }

    public int getLength() {
        return LENGTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

}
