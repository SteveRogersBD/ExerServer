package com.example.Parent.parentUtil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {

    Optional<Parent>findByEmail(String email);

}
