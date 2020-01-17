import java.util.Comparator;
import java.util.LinkedList;

public class RPESorter<T> implements RPESorterInterface {

    private Comparator comparator;

    public RPESorter(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public LinkedList extract(LinkedList in) {
        T element = (T) in.remove();
    }

    @Override
    public LinkedList merge(LinkedList a, LinkedList b) {
        return null;
    }

    @Override
    public LinkedList sort(LinkedList in) {
        return null;
    }
}
