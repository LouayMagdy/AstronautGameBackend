package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.NormalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<NormalUser, Integer> {
    boolean existsNormalUsersByUserName(String userName);
    NormalUser findNormalUserByUserName(String userName);
}
