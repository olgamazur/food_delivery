package controllers;

import com.food.FoodApp;
import com.food.controllers.ClientController;
import com.food.dto.MealDto;
import com.food.exсeptions.ExceptionHandlers;
import com.food.services.MealService;
import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.IntTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FoodApp.class)
@AutoConfigureMockMvc
public class ClientControllerTest {
    @InjectMocks
    @Autowired
    private ClientController clientController;


    @MockBean
    private MealService mealService;

    private MockMvc mockMvc;

    private List<MealDto> mealDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .setControllerAdvice(new ExceptionHandlers())
                .build();
    }

    @DisplayName("Test getMeals -> Ok, with 2 found")
    @Test
    void testGetPrivileges_expectedOkWithBody() throws Exception {
        MealDto mealDto1 = IntTestUtils.generateNextMealDtoWithoutId(1L);
        MealDto mealDto2 = IntTestUtils.generateNextMealDtoWithoutId(2L);
        mealDtos.add(mealDto1);
        mealDtos.add(mealDto2);
        Mockito.when(mealService.getMeals()).thenReturn(mealDtos);
        mockMvc.perform(get("/client/meal/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        Mockito.verify(mealService, times(1)).getMeals();
        Mockito.verifyNoMoreInteractions(mealService);
    }

}
