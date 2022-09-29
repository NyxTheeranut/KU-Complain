package ku.cs.services.filter;

public interface Filterer<T> {
    boolean found(T obj, String filter);

    String getName();
}
