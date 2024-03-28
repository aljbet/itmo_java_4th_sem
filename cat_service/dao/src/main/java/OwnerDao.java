import java.util.List;

public class OwnerDao implements Dao<Owner> {
    @Override
    public List<Owner> getAll() {
        return Init.get_em().createQuery("from Owner", Owner.class).getResultList();
    }

    @Override
    public Owner getByName(String name) {
        return null;
    }

    @Override
    public void create(Owner entity) {

    }
}
