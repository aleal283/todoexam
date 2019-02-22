package com.ipersof.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipersof.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
