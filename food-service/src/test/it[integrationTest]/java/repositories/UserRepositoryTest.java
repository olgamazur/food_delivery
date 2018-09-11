package repositories;

import com.food_delivering.FoodApp;
import com.food_delivering.entities.User;
import com.food_delivering.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FoodApp.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository usersRepository;
    private String userPass= "pass";
    private String userName = "name1";

    @BeforeEach
    void init() {
       User user=new User();
       user.setName(userName);
       user.setPassword(userPass);
        usersRepository.save(user);
    }
    @DisplayName("Test existByUsername - true")
    @Test
    void testExistByUsername_expectedTrue() {
        Boolean bool = usersRepository.existsUserByName(userName);
        Assertions.assertTrue(bool);
    }
}
