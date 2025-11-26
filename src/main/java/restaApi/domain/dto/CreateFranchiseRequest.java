package restaApi.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CreateFranchiseRequest {
    @NotBlank
    private String name;
}
