package com.ravipas.statsService.services;

import com.ravipas.statsService.dto.StatsDTO;
import com.ravipas.statsService.models.UserScore;

import java.util.List;

public interface IStatsService {
    public StatsDTO getStatsForUser(String userId);
    public List<StatsDTO> getTopStats(Integer limit);
    public UserScore incrementUserWins(String userId);
    public UserScore incrementUserLoses(String userId);
    public UserScore incrementUserTies(String userId);

}
