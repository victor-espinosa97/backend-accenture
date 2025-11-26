package restaApi.service;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductService {

    private final FranchiseRepository repo;

    public ProductService(FranchiseRepository repo) {
        this.repo = repo;
    }

    public Mono<Product> addProduct(String franchiseId, String branchId, CreateProductRequest req) {
        return repo.findById(franchiseId)
                .flatMap(fr ->
                        Flux.fromIterable(fr.getBranches())
                                .filter(b -> b.getId().equals(branchId))
                                .next()
                                .flatMap(branch -> {
                                    Product p = Product.builder()
                                            .id(UUID.randomUUID().toString())
                                            .name(req.getName())
                                            .stock(req.getStock())
                                            .build();
                                    branch.getProducts().add(p);
                                    return repo.save(fr).map(saved -> p);
                                })
                );
    }

    public Mono<Void> removeProduct(String franchiseId, String branchId, String productId) {
        return repo.findById(franchiseId)
                .flatMap(fr -> {
                    boolean removed = fr.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .map(b -> b.getProducts().removeIf(p -> p.getId().equals(productId)))
                            .orElse(false);

                    if (removed) {
                        return repo.save(fr).then();
                    }
                    return Mono.empty();
                });
    }

    public Mono<Product> updateProductStock(String franchiseId, String branchId, String productId, UpdateStockRequest req) {
        return repo.findById(franchiseId)
                .flatMap(fr ->
                        Flux.fromIterable(fr.getBranches())
                                .filter(b -> b.getId().equals(branchId))
                                .next()
                                .flatMap(branch ->
                                        Flux.fromIterable(branch.getProducts())
                                                .filter(p -> p.getId().equals(productId))
                                                .next()
                                                .flatMap(p -> {
                                                    p.setStock(req.getStock());
                                                    return repo.save(fr).map(saved -> p);
                                                })
                                )
                );
    }

    public Mono<Product> updateProductName(String franchiseId, String branchId, String productId, String newName) {
        return repo.findById(franchiseId)
                .flatMap(fr ->
                        Flux.fromIterable(fr.getBranches())
                                .filter(b -> b.getId().equals(branchId))
                                .next()
                                .flatMap(branch ->
                                        Flux.fromIterable(branch.getProducts())
                                                .filter(p -> p.getId().equals(productId))
                                                .next()
                                                .flatMap(p -> {
                                                    p.setName(newName);
                                                    return repo.save(fr).map(saved -> p);
                                                })
                                )
                );
    }
}

