package com.starterkit.springboot.brs.repository;

import com.starterkit.springboot.brs.models.FirebaseUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseUserRepository extends CrudRepository<FirebaseUser, String> {

    FirebaseUser findByEmail(String email);
}
