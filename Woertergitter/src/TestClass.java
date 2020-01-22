public class TestClass {

    public static void fillTest(WordSearch w) {
        Field f = w.getField();
        f.setChar(0,0, 'C');
        f.setChar(1,0, 'G');
        f.setChar(2,0, 'T');
        f.setChar(3,0, 'Z');
        f.setChar(0,1, 'Q');
        f.setChar(1,1, 'F');
        f.setChar(2,1, 'G');
        f.setChar(3,1, 'e'); //lowercase letter
        f.setChar(0,2, 'A');
        f.setChar(1,2, 'R');
        f.setChar(2,2, 'T');
        f.setChar(3,2, 'Z');
        f.setChar(0,3, 'o'); //lowercase letter
        f.setChar(1,3, 'F');
        f.setChar(2,3, 'G');
        f.setChar(3,3, 'e');

        w.addToWordlist(new Word("Wasser"));
        w.addToWordlist(new Word("Intellij"));
        w.addToWordlist(new Word("Television"));
        w.addToWordlist(new Word("Suppenknecht", Direction.LEFT, 2, 2));
    }

}
