/**
 * This class saves a "field" of a normal word-search as an two-dimensional array {@link FIELD} and has methods to change
 * and share its contents.
 */

@SuppressWarnings("JavadocReference")
public class Field {

    // Attributes ---------------------------------------------------------------------------------

    private final char[][] FIELD; // Feldgröße ist nicht veränderbar, aber der Inhalt
    private final int LENGTH, HEIGHT;


    // Constructors -------------------------------------------------------------------------------

    /**
     * Creates an instance of Field of given {@literal length} and {@literal height}. The coordinate-system representing this field starts with
     * (0/0) in the <b>top-left</b> corner.
     *
     * @param length length of the Field
     * @param height height of the Field
     * @throws IllegalArgumentException if either the given length or height are below 0
     */

    public Field (int length, int height) throws IllegalArgumentException {
        if (length < 0) {
            throw new IndexOutOfBoundsException("length darf nicht kleiner als 0 sein");
        } else if (height < 0) {
            throw new IndexOutOfBoundsException("height darf nicht kleiner als 0 sein");
        }
        this.FIELD = new char[length][height];
        this.LENGTH = length;
        this.HEIGHT = height;
    }


    // Methods ------------------------------------------------------------------------------------

    /**
     * Places a character {@literal c} in the given position (x/y) inside {@link FIELD}. Only the 26 letters of the alphabet
     * are accepted as characters and saved as uppercase characters. The method throws an {@link IllegalArgumentException}
     * if other characters outside of the given scope are placed in the {@link FIELD}.
     *
     * @param x x-position in FIELD
     * @param y y-position in FIELD
     * @param c the character that is to be inserted
     * @throws IllegalArgumentException if {@literal c} is not a letter of the alphabet
     * @throws IndexOutOfBoundsException if {@literal x} is below 0 or equal to or greater than {@link LENGTH} or if {@literal y}
     * is below 0 or equal to or greater than {@link HEIGHT}
     */

    public void setChar(int x, int y, char c) throws IllegalArgumentException, IndexOutOfBoundsException {
        withinArray(x, y);
        if (c < 65 || c > 122 || (c < 97 && c > 90)) {
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

    /**
     * Helper method to check whether the coordinates {@literal x} and {@literal y} are within the array {@link FIELD}. If that is
     * not the case, it throws an {@link IndexOutOfBoundsException}.
     *
     * @param x x-position in the {@link FIELD}
     * @param y y-position in the {@link FIELD}
     * @throws IndexOutOfBoundsException if {@literal x} or {@literal y} are outside of {@link FIELD}
     */

    private void withinArray(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= LENGTH) {
            throw new IndexOutOfBoundsException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= HEIGHT) {
            throw new IndexOutOfBoundsException("y liegt außerhalb des Feldes");
        }
    }

    // Getters ------------------------------------------------------------------------------------

    /**
     * Returns the respective character at the given position, but throws an {@link IndexOutOfBoundsException} when
     * {@literal x} or {@literal y} are not within {@link FIELD}.
     *
     * @param x x-position in FIELD
     * @param y y-position in FIELD
     * @return the character at the given position
     * @throws IndexOutOfBoundsException if {@literal x} is below 0 or equal to or greater than {@link LENGTH} or if {@literal y}
     * is below 0 or equal to or greater than {@link HEIGHT}
     */

    public char getChar(int x, int y) throws IndexOutOfBoundsException {
        withinArray(x, y);
        return FIELD[x][y];
    }

    public int getLength() {
        return LENGTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

}
