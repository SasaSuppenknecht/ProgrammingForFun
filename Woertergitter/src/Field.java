public class Field {
    private final char[][] FIELD; // Feldgröße ist nicht veränderbar, aber der Inhalt
    private final int LENGTH, HEIGHT;

    public Field (int length, int height) throws IllegalArgumentException {
        if (length < 1) {
            throw new IllegalArgumentException("length darf nicht kleiner als 1 sein");
        } else if (height < 1) {
            throw new IllegalArgumentException("height darf nicht kleiner als 1 sein");
        }
        this.FIELD = new char[length][height];
        this.LENGTH = length;
        this.HEIGHT = height;
    }

    public void setChar(int x, int y, char c) throws IllegalArgumentException {
        if (x < 0 || x >= LENGTH) {
            throw new IllegalArgumentException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("y liegt außerhalb des Feldes");
        } else if (c < 65 || c > 122 || (c < 97 && c > 90)) {
            throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden");
        }

        if (c >= 97) {
            c = Character.toUpperCase(c);
        }
        FIELD[x][y] = c;
    }

    public char getChar(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x >= LENGTH) {
            throw new IllegalArgumentException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("y liegt außerhalb des Feldes");
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
