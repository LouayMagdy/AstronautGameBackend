package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.repo;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model.RankingUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RankRepository extends CrudRepository<RankingUser, Integer> {
    @Override
    Optional<RankingUser> findById(Integer iD);

    @Override
    boolean existsById(Integer iD);

    @Transactional
    @Modifying
    @Query("update RankingUser r set r.avgLife = ?1, r.avgCollectedFood = ?2, r.gamesPlayedNum = ?3 where r.id = ?4")
    int updateAvgLifeAndAvgCollectedFoodAndGamesPlayedNumById(int avgLife, int avgCollectedFood, int gamesPlayedNum, int id);

}
