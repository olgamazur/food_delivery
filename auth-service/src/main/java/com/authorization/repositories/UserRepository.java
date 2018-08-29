package com.authorization.repositories;

import com.authorization.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO auth_users_roles(username, role) VALUES (?1, ?2) ON CONFLICT DO NOTHING", nativeQuery = true)
    void addRole(String username, String role);
}
