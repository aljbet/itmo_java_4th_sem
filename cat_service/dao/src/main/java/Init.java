import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Init {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory get_emf() {
        return entityManagerFactory;
    }

    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("catservice");
    }
}
