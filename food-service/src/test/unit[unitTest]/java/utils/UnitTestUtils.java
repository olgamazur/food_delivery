package utils;

import com.food.entities.Meal;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.NoArgsConstructor;

import java.util.Random;
@NoArgsConstructor
public final class UnitTestUtils {

    private static Long RANDOM_SEED = new Random().nextLong();

    private static EnhancedRandom enhancedRandom = EnhancedRandomBuilder
            .aNewEnhancedRandomBuilder()
            .stringLengthRange(5, 10)
            .collectionSizeRange(1, 5)
            .seed(RANDOM_SEED)
            .build();

    public static Meal generateNextMealWithoutId(Long id) {
        Meal meal = enhancedRandom.nextObject(Meal.class, "id");
        if (id != null) {
            meal.setId(id);
        }
        return meal;
    }
}
