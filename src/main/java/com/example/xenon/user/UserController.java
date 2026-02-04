package com.example.xenon.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id, UriComponentsBuilder uriBuilder) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        User user = optionalUser.get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
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
        if (userService.findById(id).isEmpty())
            return ResponseEntity.ok().build();

        userService.deleteUser(id);
        return new ResponseEntity<>("Product was successfully deleted", HttpStatus.ACCEPTED);
    }


}
