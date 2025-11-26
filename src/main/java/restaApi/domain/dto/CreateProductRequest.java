package restaApi.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class CreateProductRequest {
    @NotBlank
    private String name;
    @Min(0)
    private int stock;
}
