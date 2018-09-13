package com.food.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ClientOrder")
public class ClientOrder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "client_meal", joinColumns = @JoinColumn(name = "client_order_id"))
    @MapKeyJoinColumn(name = "meal_id")
    @Column(name = "amount")
    private Map<Meal, Long> meals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")

    private LocalDateTime createdDate;

    @Column(name = "total_amount")
    private Long totalAmount;

}
