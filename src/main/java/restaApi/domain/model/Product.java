package restaApi.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 0, message = "Stock must be >= 0")
    private int stock;
}

