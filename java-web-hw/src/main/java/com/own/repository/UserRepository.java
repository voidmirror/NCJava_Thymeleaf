package com.own.repository;

import com.own.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM usertable u WHERE u.first_name = :first_name and u.last_name = :last_name", nativeQuery = true)
    List<User> retrieveByFirstLastName(@Param("first_name") String first_name, @Param("last_name") String last_name);

}
