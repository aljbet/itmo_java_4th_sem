import java.util.List;

public interface Dao<T> {
    List<T> getAll();
//    T getByID(int id);
    T getByName(String name);
    void create(T entity);
}
