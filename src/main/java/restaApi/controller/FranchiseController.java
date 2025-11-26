package restaApi.controller;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.service.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> createFranchise(@RequestBody CreateFranchiseRequest req) {
        return franchiseService.createFranchise(req);
    }

    @PutMapping("/{franchiseId}/name")
    public Mono<Franchise> updateFranchiseName(
            @PathVariable String franchiseId,
            @RequestBody CreateFranchiseRequest req) {
        return franchiseService.updateFranchiseName(franchiseId, req.getName());
    }

    @GetMapping("/{franchiseId}/top-products")
    public Flux<ProductWithBranchResponse> topProductsByFranchise(
            @PathVariable String franchiseId) {
        return franchiseService.topProductsByFranchise(franchiseId);
    }
}


