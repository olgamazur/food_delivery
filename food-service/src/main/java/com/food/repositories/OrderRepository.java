package com.food.repositories;

import com.food.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<ClientOrder, Long> {
    Optional<ClientOrder> findClientOrderByUserId(Long id);
}
