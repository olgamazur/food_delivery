package services;

import com.food_delivering.dto.MealDto;
import com.food_delivering.entities.Meal;
import com.food_delivering.repositories.MealRepository;
import com.food_delivering.services.MealService;
import com.food_delivering.utils.EntityConverter;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.UnitTestUtils;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceUnitTest {
    @InjectMocks
    private MealService mealService;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private EntityConverter entityConverter;

    private Meal meal1;
    private Meal meal2;
    private MealDto mealDto1;
    private MealDto mealDto2;

    @Before
    public void init() {
        meal1 = UnitTestUtils.generateNextMealWithoutId(1L);
        mealDto1 = new MealDto(meal1.getId(), meal1.getName(), meal1.getType(), meal1.getCost());
        meal2 = UnitTestUtils.generateNextMealWithoutId(2L);
        mealDto2 = new MealDto(meal2.getId(), meal2.getName(), meal2.getType(), meal2.getCost());
    }

    @DisplayName("Test getMeals() - found two")
    @Test
    public void testGetMeals_expectedTwoFound() {
        List<Meal> dbReturnedList = Lists.newArrayList(meal1, meal2);
        when(mealRepository.findAll()).thenReturn(dbReturnedList);
        List<MealDto> expectedList = Lists.newArrayList(mealDto1, mealDto2);
        when(entityConverter.transformMeal(meal1)).thenReturn(mealDto1);
        when(entityConverter.transformMeal(meal2)).thenReturn(mealDto2);
        List<MealDto> mealDtoList = mealService.getMeals();

        Assertions.assertNotNull(mealDtoList);
        Assertions.assertEquals(expectedList.size(), mealDtoList.size());
        Assertions.assertTrue(mealDtoList.containsAll(expectedList));
        verify(entityConverter, times(1)).transformMeal(meal1);
        verify(entityConverter, times(1)).transformMeal(meal2);
        verifyNoMoreInteractions(entityConverter);
        verify(mealRepository, times(1)).findAll();
        verifyNoMoreInteractions(mealRepository);
    }
}
