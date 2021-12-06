package com.aaa.dining.repositories;

import com.aaa.dining.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
