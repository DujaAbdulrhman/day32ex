package com.example.usersystem.Repository;

import com.example.usersystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

    List<User> findUserByAgeLessThan(Integer age);

    List<User> findUserByRole(String role);


    @Query("select u from User u where u.role=?1")
    List<User> rolegetter(String role);

    User findByUsername(String username);


}
