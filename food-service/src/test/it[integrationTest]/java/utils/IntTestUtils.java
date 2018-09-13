package utils;

import com.food.dto.MealDto;
import com.food.entities.Meal;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public final class IntTestUtils {

    private static Long RANDOM_SEED = new Random().nextLong();

    private static EnhancedRandom enhancedRandom = EnhancedRandomBuilder
            .aNewEnhancedRandomBuilder()
            .stringLengthRange(5, 10)
            .collectionSizeRange(1, 5)
            .seed(RANDOM_SEED)
            .build();

    public static MealDto generateNextMealDtoWithoutId(Long id) {
        MealDto meal = enhancedRandom.nextObject(MealDto.class, "id");
        if (id != null) {
            meal.setId(id);
        }
        return meal;
    }
}
