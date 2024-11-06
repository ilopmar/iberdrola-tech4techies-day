package com.example.iberdrola;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  List<User> findAllUsers() {
    return userService.listAll();
  }

  @GetMapping("/users/{name}")
  public ResponseEntity<User> findByUserName(@PathVariable String name) {
    return userService.findByName(name)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public User saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }
}
