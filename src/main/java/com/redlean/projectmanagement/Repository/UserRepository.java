package com.redlean.projectmanagement.Repository;

import com.redlean.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name = :name")
    Optional<User> findUserWithName(@Param("name") String name);
}
