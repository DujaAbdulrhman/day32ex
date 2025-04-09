package com.example.usersystem.Controller;


import com.example.usersystem.ApiResponse.Api;
import com.example.usersystem.Model.User;
import com.example.usersystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usermanagement")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity getuser(){
        if (userService.getusers()==null){
            return ResponseEntity.status(400).body(new Api("you should add a user first"));
        }
        return ResponseEntity.status(200).body(userService.getusers());
    }

    @PostMapping("/add")
    public ResponseEntity adduser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new Api("User added successfully"));

    }
    //TODO: ارجعي لها فيها لوجيك ايرور

    @GetMapping("/get/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        User user = userService.getByEmail(email);
        if (user == null) {
            return ResponseEntity.status(400).body(new Api("the email not found"));
        }
        return ResponseEntity.status(200).body(user);
    }


    @GetMapping("/getcheck")
    public ResponseEntity checkUandP(@RequestParam String username, @RequestParam String password) {
        boolean valid = userService.checkUandP(username, password);
        if (valid) {
                        return ResponseEntity.status(200).body(new Api("Username and password are right"));

        }
        return ResponseEntity.status(400).body(new Api("Invalid username or password"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
                       return ResponseEntity.status(200).body(new Api("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new Api("User not found"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean updated = userService.updateuser(user);
        if (updated) {
                       return ResponseEntity.status(200).body(new Api("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new Api("User not found"));
    }
    @GetMapping("/age/{age}")
    public ResponseEntity getUsersByAge(@PathVariable Integer age) {
        List<User> users = userService.getUsersByAge(age);
        return ResponseEntity.status(200).body(users)
    }

    @GetMapping("/role/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role){
        List<User> users = userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.status(400).body(new Api("not found "));
        }
        return ResponseEntity.status(200).body(users);
    }








}

