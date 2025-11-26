package restaApi.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CreateBranchRequest {
    @NotBlank
    private String name;
}
