package food_delivering.contollers;

import food_delivering.dto.ClientDto;
import food_delivering.dto.CreatedMealDto;
import food_delivering.dto.MealDto;
import food_delivering.services.ClientService;
import food_delivering.services.MealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ClientService clientService;
    private final MealService mealService;

    @GetMapping(value = "/client/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto getClient(@PathVariable @Valid Long id) {
        return clientService.getClient(id);
    }

    @GetMapping(value = "/meal/list")
    public List<MealDto> getMeals() {
        return mealService.getMeals();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/meal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MealDto addMeal(@RequestBody @Valid CreatedMealDto mealDto) {
        return mealService.addNewMeal(mealDto);
    }

    @GetMapping(value = "/meal/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MealDto getMeal(@PathVariable @Valid String name) {
        return mealService.getMeal(name);
    }
    @GetMapping(value = "/meal/drinks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealDto> getDrinks() {
        return mealService.getDrinks();
    }
    @DeleteMapping(value = "/meal/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MealDto deleteMeal(@PathVariable String name) {
        return mealService.deleteMeal(name);
    }
}
