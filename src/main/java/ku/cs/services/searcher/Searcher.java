package ku.cs.services.searcher;

public interface Searcher<T> {
    boolean found(T obj, String name);
}
