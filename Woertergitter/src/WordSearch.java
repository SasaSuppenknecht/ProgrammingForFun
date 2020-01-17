import java.util.LinkedList;
import java.util.ListIterator;

public class WordSearch {

    private Field field;
    private LinkedList<Word> words;

    public WordSearch (){       //leerer Konstruktor, für Benutzeroberfläche
        field = null;
        words = new LinkedList<>();
    }

    public WordSearch (int length, int height){
        field = new Field(length, height);
        words = new LinkedList<>();
    }

    public void printWordlist() {
        for (int i = 0; i < words.size(); i++){
            System.out.println(i + ". " + words.get(i).getWord());
        }
    }

    public void addToWordlist(Word word) { // sortiert einfuegen!!!
        if (!words.contains(word)) {
           ListIterator<Word> listIterator = words.listIterator(0);
           while (listIterator.hasNext()) {
               Word curWord = listIterator.next();
               if (curWord.getWord().length() == word.getWord().length()) {
                   //words.add(word);
                   listIterator.add(word);
               }
           }
        }
    }

    public boolean removeFromWordlist(int index) {return false;}

    public void clearWordlist() {}

    public boolean createField(int length, int height) {return false;}

    public void createMinimalField() {}

    public Field getField() {
        return field;
    }

    public Word[] getWords() {
        return (Word[])  words.toArray();
        // eventuell auch ueber "return LinkedList" realisierbar
    }

    //public boolean setField() {return false;}  //macht Saver

    public boolean solve() {return false;}

    private void printSolution() {}
}






















