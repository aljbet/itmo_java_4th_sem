import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class OwnerDao implements Dao<Owner> {
    @Override
    public List<Owner> getAll() {
        EntityManager em = Init.get_emf().createEntityManager();
        List<Owner> res = em.createQuery("from Owner", Owner.class).getResultList();
        em.close();
        return res;
    }

    @Override
    public Owner getByName(String name) {
        EntityManager em = Init.get_emf().createEntityManager();
        Owner res;
        try {
            res = em.createQuery("from Owner where name=:name", Owner.class)
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
    public void create(Owner entity) {
        EntityManager em = Init.get_emf().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Owner entity) {
        EntityManager em = Init.get_emf().createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }
}
