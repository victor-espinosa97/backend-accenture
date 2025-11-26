package restaApi.controller;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises/{franchiseId}/branches/{branchId}/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addProduct(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @RequestBody CreateProductRequest req) {
        return productService.addProduct(franchiseId, branchId, req);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @PathVariable String productId) {
        return productService.removeProduct(franchiseId, branchId, productId);
    }

    @PatchMapping("/{productId}/stock")
    public Mono<Product> updateStock(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @PathVariable String productId,
            @RequestBody UpdateStockRequest req) {
        return productService.updateProductStock(franchiseId, branchId, productId, req);
    }

    @PutMapping("/{productId}/name")
    public Mono<Product> updateProductName(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @PathVariable String productId,
            @RequestBody CreateProductRequest req) {
        return productService.updateProductName(franchiseId, branchId, productId, req.getName());
    }
}

