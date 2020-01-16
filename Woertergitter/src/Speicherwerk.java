import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Speicherwerk {

    public static void save (Woertersuche woertersuche) throws IOException {
        String userName = ""; // needs to be improved/found
        String path = "C:\\Users\\"+ userName +"\\Documents\\GitterSave.txt";

        File file = new File(path);     //erzeugt Objekt, an angegebenen Pfad
        file.createNewFile();       //erstellt Datei, falls sie nicht existiert
        FileWriter fileWriter = new FileWriter(path);

        BufferedWriter writer = new BufferedWriter(fileWriter);

        Feld feld = woertersuche.getFeld();
        int height = feld.getHeight();
        int length = feld.getLength();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                writer.write(feld.getChar(j,i) + " ");
            }
            writer.newLine();
        }
        writer.newLine();

        for (Wort word : woertersuche.getWoerter()) {
            String s = word.getWord();
            writer.write(s);
            writer.newLine();
        }
    }

    public static void load (String path){ // !!! Rueckgabe eigentlich ein Textdokument/ file !!!

    }
    public static void savaAndLoad(Woertersuche woertersuche, String path){

    }
}
