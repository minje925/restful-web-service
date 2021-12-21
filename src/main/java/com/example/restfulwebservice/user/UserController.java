package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService service;

    @GetMapping(value ="/users")
    public List<User> getAllUsers() {
        System.out.println("user 호출.");
        List<User> users = service.findAll();
        return users;
    }

    @GetMapping(value = "/users/{id}")
    public User getOneUser(@PathVariable int id) {
        User one = service.findOne(id);
        if(one == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return one;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User deltedUser = service.deleteById(id);

        if(deltedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

    }
}
