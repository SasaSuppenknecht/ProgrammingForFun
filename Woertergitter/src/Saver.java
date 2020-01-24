import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class saves and loads {@link WordSearch}es into and from the filesystem. It generally creates a new directory called "GitterSave", in which it
 * saves the {@link WordSearch}es as ".txt"-files.
 */

public class Saver {

    // can be used in UserInterface to make sure the directory "GitterSaves" exists
    public static void createDirectory() throws IOException {
        String userName = System.getProperty("user.name");
        String dirPathString = "C:\\Users\\" + userName + "\\Documents\\GitterSaves";
        Path dirPath = Paths.get(dirPathString);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
    }

    public static void save (WordSearch w, String fileName) throws IllegalArgumentException, IOException {
        createDirectory();
        fileNameTester(fileName);

        String userName = System.getProperty("user.name");
        String filePathString = "C:\\Users\\" + userName + "\\Documents\\GitterSaves\\" + fileName + ".txt";

        File saveFile = new File(filePathString);     //erzeugt Objekt, an angegebenen Pfad
        if (saveFile.exists()) {
            saveFile.delete();
        }
        saveFile.createNewFile();       //erstellt Datei, falls sie nicht existiert

        FileWriter fileWriter = new FileWriter(filePathString);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        Field field = w.getField();
        int length = field.getLength();
        int height = field.getHeight();

        writer.write(String.valueOf(length));
        writer.newLine();
        writer.write(String.valueOf(height));
        writer.newLine();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                System.out.println(field.getChar(j, i));
                writer.write( field.getChar(j, i) + " ");
            }
            writer.newLine();
        }
        writer.newLine();

        for (Word word : w.getWords()) {
            String s = word.getWord();
            writer.write(s + " ");
            if (word.getDirection() != null) {
                writer.write(word.getDirection() + " ");
                writer.write(word.getX() + " ");
                writer.write(word.getY() + " ");
            }
            writer.newLine();
        }

        writer.close();
    }

    public static WordSearch load (String fileName, boolean wordlistOnly) throws IllegalArgumentException, IOException { // !!! Rueckgabe eigentlich ein Textdokument/ file !!!
        fileNameTester(fileName);

        String userName = System.getProperty("user.name");
        String pathString = "C:\\Users\\"+ userName +"\\Documents\\GitterSaves\\";

        File loadFile = new File(pathString + fileName + ".txt");
        if (!loadFile.exists()) {
            throw new FileNotFoundException("Dateiname nicht bekannt.");
        }

        BufferedReader reader = new BufferedReader(new FileReader(loadFile));
        WordSearch w;

        if (!wordlistOnly) {
            int length, height;
            try {
                length = Integer.parseInt(reader.readLine());
                height = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Datei ist korrumpiert.");
            }

            w = new WordSearch(length, height);
            Field f = w.getField();
            for (int i = 0; i < height; i++) {
                String s = reader.readLine();
                for (int j = 0; j < length; j++) {
                    f.setChar(j, i, s.charAt(j * 2), false);
                }
            }
            reader.readLine();
        } else {
          w = new WordSearch();
        }

        while (true) {
            String s = reader.readLine();
            if (s == null) break;

            Scanner scanner = new Scanner(s);
            Word word = null;
            String wordString = scanner.next();
            if (scanner.hasNext()) {
                String dirString = scanner.next();
                Direction dir = associateStringWithDirection(dirString);

                int xPos = scanner.nextInt();
                int yPos = scanner.nextInt();

                word = new Word(wordString, dir, xPos, yPos);
            }

            if (word == null) {
                word = new Word(wordString);
            }
            w.addToWordlist(word);
            scanner.close();
        }

        reader.close();
        return w;
    }

    private static void fileNameTester(String fileName) throws IllegalArgumentException {
        char[] chars = fileName.toCharArray();
        for (char c: chars) {
            if (c < 65 || c > 122 || (c > 90 && c < 97)) {
                throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden.");
            }
        }
        if (fileName.contains(".txt")) {
            throw new IllegalArgumentException("Dateiname ohne Dateityp angeben.");
        }
    }

    private static Direction associateStringWithDirection(String str) {
        switch (str) {
            case "DOWN":
                return Direction.DOWN;
            case "DOWNLEFT":
                return Direction.DOWNLEFT;
            case "DOWNRIGHT":
                return Direction.DOWNRIGHT;
            case "LEFT":
                return Direction.LEFT;
            case "RIGHT":
                return Direction.RIGHT;
            case "UP":
                return Direction.UP;
            case "UPLEFT":
                return Direction.UPLEFT;
            case "UPRIGHT":
                return Direction.UPRIGHT;
            default:
                return null;
        }
    }

    public static WordSearch saveAndLoad(WordSearch w, String fileSaveName, String fileLoadName) throws IOException , IllegalArgumentException {
        WordSearch newW = load(fileLoadName, false);
        save(w, fileSaveName);
        return newW;
    }
}
