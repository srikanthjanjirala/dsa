package com.dsa.practice.controller;

import com.dsa.practice.dto.CourseIdsRequest;
import com.dsa.practice.dto.UserDTO;
import com.dsa.practice.model.Role;
import com.dsa.practice.model.User;
import com.dsa.practice.repository.RoleRepository;
import com.dsa.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/{userId}/courses")
    public ResponseEntity<User> assignCourses(
            @PathVariable Long userId,
            @RequestBody CourseIdsRequest request) {
        User updatedUser = userService.assignCourses(userId, request.getCourseIds());
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{city}")
    public List<User> getUserByCity(@PathVariable String city){
        return userService.getUserByCity(city);
    }

    @GetMapping("/find-by-email")
    public Optional<User> GetUserWithName(@RequestParam String email){
        return userService.getUserByName(email);
    }
}
