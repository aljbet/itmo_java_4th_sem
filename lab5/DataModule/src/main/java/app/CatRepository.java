package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Cat findCatByName(String name);
    List<Cat> findCatsByBreed(String breed);
    List<Cat> findCatsByColor(String color);
}
