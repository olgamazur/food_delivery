package com.food.repositories;

import com.food.entities.ClientMeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientMealRepository extends JpaRepository<ClientMeals, Long> {

    @Query(value = "SELECT * FROM client_meal WHERE client_order_id = ?1 and meal_id = ?2",nativeQuery = true)
    Optional<ClientMeals>  findByClientOrderIdAndMealId( Long clientOrderId,  Long mealId);

}
