import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Init {
    private static EntityManager entityManager;

    public static EntityManager get_em() {
        return entityManager;
    }

    public static void init() {
        entityManager = Persistence.createEntityManagerFactory("catservice").createEntityManager();
    }
}
