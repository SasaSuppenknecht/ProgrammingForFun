public class Feld {
    private final char[][] FELD; // Feldgröße ist nicht veränderbar, aber der Inhalt

    public Feld (int length, int height) throws IllegalArgumentException {
        if (length < 1) {
            throw new IllegalArgumentException("length darf nicht kleiner als 1 sein");
        } else if (height < 1) {
            throw new IllegalArgumentException("height darf nicht kleiner als 1 sein");
        }
        FELD = new char[length][height];
    }

    public void setChar(int x, int y, char c) throws IllegalArgumentException {
        if (x < 0 || x >= FELD.length) {
            throw new IllegalArgumentException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= FELD[0].length) {
            throw new IllegalArgumentException("y liegt außerhalb des Feldes");
        } else if (c < 65 || c > 122 || (c < 97 && c > 90)) {
            throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden");
        }

        if (c >= 97) {
            c = Character.toUpperCase(c);
        }
        FELD[x][y] = c;
    }

    public char getChar(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x >= FELD.length) {
            throw new IllegalArgumentException("x liegt außerhalb des Feldes");
        } else if (y < 0 || y >= FELD[0].length) {
            throw new IllegalArgumentException("y liegt außerhalb des Feldes");
        }
        return FELD[x][y];
    }

    public int getLength() {
        return FELD.length;
    }

    public int getHeight() {
        return FELD[0].length;
    }
}
