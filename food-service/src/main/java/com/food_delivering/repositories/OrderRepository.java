package com.food_delivering.repositories;

import com.food_delivering.entities.ClientOrder;
import com.food_delivering.entities.Meal;
import com.food_delivering.entities.User;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<ClientOrder, Long> {
    Optional<ClientOrder> findClientOrderByUserId(Long id);
}
