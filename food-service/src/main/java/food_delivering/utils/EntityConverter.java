package food_delivering.utils;


import food_delivering.dto.ClientDto;
import food_delivering.dto.MealDto;
import food_delivering.entities.Meal;
import food_delivering.entities.User;
import food_delivering.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityConverter {
    private final UserRepository userRepository;

    public ClientDto transformClient(User user) {
        ClientDto dto = new ClientDto(user.getName(), user.getId());
        return dto;
    }

    public MealDto transformMeal(Meal meal) {
        MealDto dto = new MealDto(meal.getId(), meal.getName(), meal.getType(), meal.getCost());
        return dto;
    }
}
