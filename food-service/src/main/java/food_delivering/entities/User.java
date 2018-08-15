package food_delivering.entities;

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
@Table(name = "client")
public class User {
    @Column(name = "client_name")
    private String name;
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
