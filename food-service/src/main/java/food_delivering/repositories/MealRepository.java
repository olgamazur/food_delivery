package food_delivering.repositories;


import food_delivering.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    boolean existsMealByName(String name);

    Optional<Meal> findMealByName(String name);

    @Query(value = "SELECT * FROM meal WHERE type = 'drink'", nativeQuery = true)
    List<Meal> findAllDrinks();

    @Query(value = "SELECT * FROM meal WHERE type = 'food'", nativeQuery = true)
    List<Meal> findAllFood();
}
