package com.example.iberdrola;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@Import(TestcontainersConfiguration.class)
class UserControllerTest {

  @LocalServerPort
  private int port;

  private RestClient restClient;

  @BeforeEach
  void init() {
    this.restClient = RestClient.create("http://localhost:%s".formatted(port));
  }

  @Test
  void createUser() {
    ResponseEntity<User> response = restClient
        .post()
        .uri("/users")
        //        .body(new User("Iván"))
        .body("{\"name\":  \"Iván\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(User.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).extracting("name").isEqualTo("Iván");
  }

}
