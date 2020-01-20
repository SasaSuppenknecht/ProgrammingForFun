import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Saver {

    public static void save (WordSearch w, String fileName) throws IOException {
        char[] chars = fileName.toCharArray();
        for (char c: chars) {
            if (c < 65 || c > 122 || (c > 90 && c < 97)) {
                throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden.");
            }
        }

        String userName = System.getProperty("user.name");
        String dirPathString = "C:\\Users\\"+ userName +"\\Documents\\GitterSaves";
        Path dirPath = Paths.get(dirPathString);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        String filePathString = dirPathString + "\\" + fileName + ".txt";

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
                writer.write( field.getChar(j,i) + " ");
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

    public static WordSearch load (String fileName, boolean wordlistOnly) throws IOException, IllegalArgumentException { // !!! Rueckgabe eigentlich ein Textdokument/ file !!!
        char[] chars = fileName.toCharArray();
        for (char c: chars) {
            if (c < 65 || c > 122 || (c > 90 && c < 97)) {
                throw new IllegalArgumentException("Nur die 26 Buchstaben des Alphabets verwenden.");
            }
        }
        if (fileName.contains(".txt")) {
            throw new IllegalArgumentException("Dateiname ohne Dateityp angeben.");
        }

        String userName = System.getProperty("user.name");
        String pathString = "C:\\Users\\"+ userName +"\\Documents\\GitterSaves\\";

        File loadFile = new File(pathString + fileName + ".txt");
        if (!loadFile.exists()) {
            throw new IllegalArgumentException("Dateiname nicht bekannt.");
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
                    f.setChar(j, i, s.charAt(j * 2));
                }
            }
            reader.readLine();
        } else {
          w = new WordSearch();
        }

        Scanner scanner;
        while (true) {
            String s = reader.readLine();
            if (s == null) break;

            scanner = new Scanner(s);
            Word word = null;
            String wordString = scanner.next();
            if (scanner.hasNext()) {
                String dirString = scanner.next();
                Direction dir;
                switch (dirString) {
                    case "DOWN":
                        dir = Direction.DOWN;
                        break;
                    case "DOWNLEFT":
                        dir = Direction.DOWNLEFT;
                        break;
                    case "DOWNRIGHT":
                        dir = Direction.DOWNRIGHT;
                        break;
                    case "LEFT":
                        dir = Direction.LEFT;
                        break;
                    case "RIGHT":
                        dir = Direction.RIGHT;
                        break;
                    case "UP":
                        dir = Direction.UP;
                        break;
                    case "UPLEFT":
                        dir = Direction.UPLEFT;
                        break;
                    case "UPRIGHT":
                        dir = Direction.UPRIGHT;
                        break;
                    default:
                        dir = null;
                }
                int xPos = scanner.nextInt();
                int yPos = scanner.nextInt();

                word = new Word(wordString, dir, xPos, yPos);
            }

            if (word == null) {
                word = new Word(wordString);
            }
            w.addToWordlist(word);
        }

        return w;
    }


    public static WordSearch saveAndLoad(WordSearch w, String fileSaveName, String fileLoadName) throws IOException , IllegalArgumentException {
        WordSearch newW = load(fileLoadName, false);
        save(w, fileSaveName);
        return newW;
    }
}
