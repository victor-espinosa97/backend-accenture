package restaApi.controller;

import restaApi.domain.dto.CreateFranchiseRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class FranchiseControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createFranchise_shouldReturnCreated() {
        CreateFranchiseRequest req = new CreateFranchiseRequest();
        req.setName("TestFranchise");

        webTestClient.post()
                .uri("/api/franchises")
                .bodyValue(req)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("TestFranchise");
    }
}
