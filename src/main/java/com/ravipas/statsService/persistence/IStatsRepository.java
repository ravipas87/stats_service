package com.ravipas.statsService.persistence;

import com.ravipas.statsService.models.UserScore;
import org.h2.engine.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface IStatsRepository extends JpaRepository<UserScore, Integer> {
    UserScore findByUserId(String userId);
    List<UserScore> findAllByOrderByWinCountDesc(Pageable pageable);

}
