package food_delivering.dto;

import lombok.Data;

@Data
public class CreatedMealDto {
    private String name;
    private String type;
    private Long cost;
}
