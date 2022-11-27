package com.ravipas.statsService.services;

import com.ravipas.statsService.dto.StatsDTO;
import com.ravipas.statsService.models.UserScore;
import com.ravipas.statsService.persistence.IStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatsService implements IStatsService {

    @Autowired
    IStatsRepository iStatsRepository;

    @Override
    public StatsDTO getStatsForUser(String userId) {
        return new StatsDTO(this.iStatsRepository.findByUserId(userId));
    }

    @Override
    public List<StatsDTO> getTopStats(Integer limit) {
        return this.iStatsRepository.findAllByOrderByWinCountDesc(Pageable.ofSize(limit)).stream().map(StatsDTO::new).collect(Collectors.toList());

    }

    @Override
    public UserScore incrementUserTies(String userId) {
        UserScore userScore=this.iStatsRepository.findByUserId(userId);
        if (userScore==null){
            userScore=new UserScore(userId);
        }
        // TODO: add transaction level logging
        log.info("incrementing ties for user: " + userId+" with current count:"+userScore.getTieCount());
        return this.iStatsRepository.save(userScore.incrementTieCount());
        //userScore.incrementTieCount();
        //this.iStatsRepository.save(userScore);
        //return userScore;
        // I had to init all values, felt like a bad pattern, in theory you could override builder method
        //return (userScore!=null) ? userScore.incrementWinCount() : this.iStatsRepository.save(UserScore.builder().userId(userId).loseCount(0).winCount(0).tieCount(0).build().incrementTieCount());
    }

    @Override
    public UserScore incrementUserWins(String userId) {
        UserScore userScore=this.iStatsRepository.findByUserId(userId);
        if (userScore==null){
            userScore=new UserScore(userId);
        }
        log.info("incrementing wins for user: " + userId+" with current count:"+userScore.getWinCount());
        return this.iStatsRepository.save(userScore.incrementWinCount());
    }

    @Override
    public UserScore incrementUserLoses(String userId) {
        UserScore userScore=this.iStatsRepository.findByUserId(userId);
        if (userScore==null){
            userScore=new UserScore(userId);
        }
        log.info("incrementing lose for user: " + userId+" with current count:"+userScore.getLoseCount());
        return this.iStatsRepository.save(userScore.incrementLoseCount());
    }

}

