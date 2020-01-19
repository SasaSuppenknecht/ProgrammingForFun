import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    private static WordSearch grid;
    private String recentlyLoaded;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        WordSearch w = Saver.load("test");
        w.print(true);
    }

    private static void printMenu() {}
}
