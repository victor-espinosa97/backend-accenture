package restaApi.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;

@Data
public class UpdateStockRequest {
    @Min(0)
    private int stock;
}
