package ku.cs.services.datasource;

public interface DataSource<T> {
    T readData();
    void writeData(T t);
    //append()?
}
