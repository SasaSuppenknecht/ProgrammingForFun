import java.io.IOException;

public class UserInterface {

    private static WordSearch grid;
    private String recentlyLoaded;

    public static void main(String[] args) throws IOException { //can be removed at the end once exception handling is done
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        WordSearch w = new WordSearch(4,4 );
        if (args.length != 0) {
            if (args[0].equals("testing")) {
                TestClass.fillTest(w);
            }
        }




        w = Saver.load("createtest", true);
        w.createField(6,6);
        w.print(true);


    }

    private static void printMenu() {}
}
