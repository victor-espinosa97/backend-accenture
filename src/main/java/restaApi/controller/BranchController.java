package restaApi.controller;

import restaApi.domain.dto.*;
import restaApi.domain.model.*;
import restaApi.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises/{franchiseId}/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Branch> addBranch(
            @PathVariable String franchiseId,
            @RequestBody CreateBranchRequest req) {
        return branchService.addBranch(franchiseId, req);
    }

    @PutMapping("/{branchId}/name")
    public Mono<Branch> updateBranchName(
            @PathVariable String franchiseId,
            @PathVariable String branchId,
            @RequestBody CreateBranchRequest req) {
        return branchService.updateBranchName(franchiseId, branchId, req.getName());
    }
}

