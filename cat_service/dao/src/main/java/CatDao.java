import java.util.List;

public class CatDao implements Dao<Cat> {
    @Override
    public List<Cat> getAll() {
        return Init.get_em().createQuery("from Cat", Cat.class).getResultList();
    }

    @Override
    public Cat getByName(String name) {
        return null;
    }

    @Override
    public void create(Cat entity) {

    }
}
