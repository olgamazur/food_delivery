package com.food_delivering.repositories;

import com.food_delivering.entities.ClientMeals;
import com.food_delivering.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientMealRepository extends JpaRepository<ClientMeals, Long> {

    @Query(value = "SELECT * FROM client_meal WHERE client_order_id = ?1 and meal_id = ?2",nativeQuery = true)
    Optional<ClientMeals>  findByClientOrderIdAndMealId( Long clientOrderId,  Long mealId);

}
