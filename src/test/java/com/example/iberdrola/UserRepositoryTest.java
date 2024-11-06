package com.example.iberdrola;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Import(TestcontainersConfiguration.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void saveUser() {
    User user = userRepository.save(new User("Iván"));

    assertThat(user.getId()).isNotNull();

    Optional<User> optUser = userRepository.findByName("Iván");
    assertThat(optUser)
        .isPresent()
        .get()
        .extracting(User::getId, User::getName)
        .containsExactly(user.getId(), user.getName());
  }

}
