import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    private static WordSearch grid;
    private String recentlyLoaded;

    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        WordSearch w = new WordSearch(4,4 );
        if (args.length != 0) {
            if (args[0].equals("testing")) {
                TestClass.fillTest(w);
            }
        }

        w = Saver.load("createtest");
        w.createField(9,9);
        w.print(false);
    }

    private static void printMenu() {}
}
