import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {

    public static void save (WordSearch w) throws IOException {
        String userName = System.getProperty("user.name");
        String path = "C:\\Users\\"+ userName +"\\Documents\\GitterSave.txt";

        File file = new File(path);     //erzeugt Objekt, an angegebenen Pfad
        file.createNewFile();       //erstellt Datei, falls sie nicht existiert
        FileWriter fileWriter = new FileWriter(path);

        BufferedWriter writer = new BufferedWriter(fileWriter);

        Field field = w.getField();
        int height = field.getHeight();
        int length = field.getLength();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                writer.write( field.getChar(j,i) + " ");
            }
            writer.newLine();
        }
        writer.newLine();

        for (Word word : w.getWords()) {
            String s = word.getWord();
            writer.write(s);
            writer.newLine();
        }
    }

    public static void load (String path){ // !!! Rueckgabe eigentlich ein Textdokument/ file !!!

    }
    public static void savaAndLoad(WordSearch w, String fileSaveName, String fileLoadName){

    }
}
