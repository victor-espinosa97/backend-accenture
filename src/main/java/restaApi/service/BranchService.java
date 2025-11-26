package restaApi.service;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class BranchService {

    private final FranchiseRepository repo;

    public BranchService(FranchiseRepository repo) {
        this.repo = repo;
    }

    public Mono<Branch> addBranch(String franchiseId, CreateBranchRequest req) {
        return repo.findById(franchiseId)
                .flatMap(fr -> {
                    Branch branch = Branch.builder()
                            .id(UUID.randomUUID().toString())
                            .name(req.getName())
                            .build();
                    fr.getBranches().add(branch);
                    return repo.save(fr).map(saved -> branch);
                });
    }

    public Mono<Branch> updateBranchName(String franchiseId, String branchId, String newName) {
        return repo.findById(franchiseId)
                .flatMap(fr ->
                        Flux.fromIterable(fr.getBranches())
                                .filter(b -> b.getId().equals(branchId))
                                .next()
                                .flatMap(b -> {
                                    b.setName(newName);
                                    return repo.save(fr).map(saved -> b);
                                })
                );
    }
}

