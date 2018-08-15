package food_delivering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MealDto {
    private Long id;
    private String name;
    private String type;
    private Long cost;
}
