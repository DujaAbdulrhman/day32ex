package com.example.usersystem.Service;


import com.example.usersystem.Model.User;
import com.example.usersystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getusers(){
        return userRepository.findAll();
    }

    public Boolean addUser(User user){
        userRepository.save(user);
        return true;
    }

    public User getByEmail(String email){
        return userRepository.findUserByEmail(email);
    }


    public Boolean updateuser(User user){
        User olduser=userRepository.findUserByEmail(user.getEmail());
        if (olduser==null){
            return false;
        }
        olduser.setAge(user.getAge());
        olduser.setRole(user.getRole());
        olduser.setEmail(user.getEmail());
        olduser.setUsername(user.getUsername());
        olduser.setPassword(user.getPassword());
        userRepository.save(olduser);
        return true;
    }

    public boolean checkUandP(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return false;
        userRepository.delete(user);
        return true;
    }

    public List<User> getUsersByAge(Integer age) {
        return userRepository.findUserByAgeLessThan(age);
    }

    public List<User> getUsersByRole(String role){
        return userRepository.rolegetter(role);
    }






}
