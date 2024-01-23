package com.spring.boot.smartcontact.dao;

import com.spring.boot.smartcontact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
