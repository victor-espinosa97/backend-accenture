package restaApi.service;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseService {

    private final FranchiseRepository repo;

    public FranchiseService(FranchiseRepository repo) {
        this.repo = repo;
    }

    public Mono<Franchise> createFranchise(CreateFranchiseRequest req) {
        Franchise f = Franchise.builder()
                .name(req.getName())
                .build();
        return repo.save(f);
    }

    public Mono<Franchise> updateFranchiseName(String franchiseId, String newName) {
        return repo.findById(franchiseId)
                .flatMap(fr -> {
                    fr.setName(newName);
                    return repo.save(fr);
                });
    }

    public Flux<ProductWithBranchResponse> topProductsByFranchise(String franchiseId) {
        return repo.findById(franchiseId)
                .flatMapMany(fr -> Flux.fromIterable(fr.getBranches()))
                .flatMap(branch -> Flux.fromIterable(branch.getProducts())
                        .map(p -> new ProductWithBranchResponse(
                                branch.getId(),
                                branch.getName(),
                                p.getId(),
                                p.getName(),
                                p.getStock()
                        ))
                )
                .sort((a,b) -> Integer.compare(b.getStock(), a.getStock()));
    }
}

