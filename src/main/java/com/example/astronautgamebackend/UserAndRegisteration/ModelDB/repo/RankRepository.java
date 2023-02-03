package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import org.springframework.data.repository.CrudRepository;

public interface RankRepository extends CrudRepository<RankingUser, Integer> {

}
