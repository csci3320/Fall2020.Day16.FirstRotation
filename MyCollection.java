
public interface MyCollection<T extends Comparable> {
    void add(T toAdd);
    int size();
    boolean isEmpty();
    void remove(T toRemove);
    boolean contains(T element);
}