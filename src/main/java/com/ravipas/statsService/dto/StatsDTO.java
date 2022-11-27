package com.ravipas.statsService.dto;

import com.ravipas.statsService.models.UserScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StatsDTO {
    private Integer winCount;
    private Integer loseCount;
    private Integer tieCount;

    public StatsDTO(UserScore userScore) {
        super();
        this.winCount= userScore.getWinCount();
        this.loseCount= userScore.getLoseCount();
        this.tieCount= userScore.getTieCount();
    }
}
