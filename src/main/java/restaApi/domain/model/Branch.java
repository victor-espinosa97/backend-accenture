package restaApi.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    private String id;

    @NotBlank(message = "Branch name is required")
    private String name;

    @Builder.Default
    private List<Product> products = new ArrayList<>();
}

