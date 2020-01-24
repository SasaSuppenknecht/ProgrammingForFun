public class TestClass {

    public static void fillTest(WordSearch w) {
        Field f = w.getField();
        f.setChar(0,0, 'C', false);
        f.setChar(1,0, 'G', false);
        f.setChar(2,0, 'T', false);
        f.setChar(3,0, 'Z', false);
        f.setChar(0,1, 'Q', false);
        f.setChar(1,1, 'F', false);
        f.setChar(2,1, 'G', false);
        f.setChar(3,1, 'e', false); //lowercase letter
        f.setChar(0,2, 'A', false);
        f.setChar(1,2, 'R', false);
        f.setChar(2,2, 'T', false);
        f.setChar(3,2, 'Z', false);
        f.setChar(0,3, 'o', false); //lowercase letter
        f.setChar(1,3, 'F', false);
        f.setChar(2,3, 'G', false);
        f.setChar(3,3, 'e', false);

        w.addToWordlist(new Word("Wasser"));
        w.addToWordlist(new Word("Intellij"));
        w.addToWordlist(new Word("Television"));
        w.addToWordlist(new Word("Suppenknecht", Direction.LEFT, 2, 2));
    }

}
