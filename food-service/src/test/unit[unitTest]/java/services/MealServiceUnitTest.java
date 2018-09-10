package services;

import com.food_delivering.dto.MealDto;
import com.food_delivering.entities.Meal;
import com.food_delivering.repositories.MealRepository;
import com.food_delivering.services.MealService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class MealServiceUnitTest {
    @InjectMocks
    private MealService mealService;
    @Mock
    private MealRepository mealRepository;
    private Meal meal1;
    private Meal meal2;
    private MealDto mealDto1;
    private MealDto mealDto2;

    @BeforeEach
    void init() {

    }

    @DisplayName("Test getMeals() - found two")
    @Test
    public void testGetMeals_expectedTwoFound() {
    }
}
