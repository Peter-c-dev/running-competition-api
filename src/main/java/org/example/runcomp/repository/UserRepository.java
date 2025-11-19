package org.example.runcomp.repository;

import org.example.runcomp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}
