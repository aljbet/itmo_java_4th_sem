import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class CatDao implements Dao<Cat> {
    @Override
    public List<Cat> getAll() {
        EntityManager em = Init.get_emf().createEntityManager();
        List<Cat> res = em.createQuery("from Cat", Cat.class).getResultList();
        em.close();
        return res;
    }

    @Override
    public Cat getByName(String name) {
        EntityManager em = Init.get_emf().createEntityManager();
        Cat res;
        try {
            res = em.createQuery("from Cat where name=:name", Cat.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        em.close();
        return res;
    }

    @Override
    public void create(Cat entity) {
        EntityManager em = Init.get_emf().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Cat entity) {
        EntityManager em = Init.get_emf().createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }
}
