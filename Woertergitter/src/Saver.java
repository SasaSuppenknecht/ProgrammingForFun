import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {

    public static void save (WordSearch w, String fileName) throws IOException {

        //todo exception handling for illegal filenames and other
        String userName = System.getProperty("user.name");
        String dirPathString = "C:\\Users\\"+ userName +"\\Documents\\GitterSaves";
        Path dirPath = Paths.get(dirPathString);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        String filePathString = dirPathString + "\\" + fileName + ".txt";

        //todo method should be able to overwrite file
        File saveFile = new File(filePathString);     //erzeugt Objekt, an angegebenen Pfad
        saveFile.createNewFile();       //erstellt Datei, falls sie nicht existiert

        FileWriter fileWriter = new FileWriter(filePathString);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        Field field = w.getField();
        int length = field.getLength();
        int height = field.getHeight();

        writer.write(length);
        writer.newLine();
        writer.write(height);
        writer.newLine();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                writer.write( field.getChar(j,i) + " ");
            }
            writer.newLine();
        }
        writer.newLine();

        //todo also needs to save solution to a word
        for (Word word : w.getWords()) {
            String s = word.getWord();
            writer.write(s);
            writer.newLine();
        }
    }

    public static void load (String loadFile){ // !!! Rueckgabe eigentlich ein Textdokument/ file !!!
        //todo exception handling for illegal/wrong path
    }
    public static void savaAndLoad(WordSearch w, String fileSaveName, String fileLoadName){

    }
}
