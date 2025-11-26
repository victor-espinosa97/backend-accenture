package restaApi.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "franchises")
public class Franchise {

    @Id
    private String id;

    @NotBlank(message = "Franchise name is required")
    private String name;

    @Builder.Default
    private List<Branch> branches = new ArrayList<>();
}
