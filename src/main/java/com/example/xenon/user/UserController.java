package com.example.xenon.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    /*
        TODO: Add DTO
            https://www.youtube.com/watch?v=Ax0pHPD5-nE
    * */

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request, UriComponentsBuilder uriBuilder) {
        User user = userService.createUser(request);

        return ResponseEntity.created(uriBuilder
                        .replacePath("/users/add/{userId}")
                        .build(Map.of("userId", user.getId())))
                .body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
    }


}
