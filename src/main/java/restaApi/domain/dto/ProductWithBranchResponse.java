package restaApi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithBranchResponse {
    private String branchId;
    private String branchName;
    private String productId;
    private String productName;
    private int stock;
}
