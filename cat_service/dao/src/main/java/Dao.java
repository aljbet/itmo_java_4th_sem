import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T getByName(String name);
    void create(T entity);
    void update(T entity);
}
