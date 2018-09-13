package com.food.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client_meal")
public class ClientMeals {
    @Id
    @Column(name = "client_order_id")
    private Long clientOrderId;

    @Column(name = "meal_id")
    private Long mealId;

}
