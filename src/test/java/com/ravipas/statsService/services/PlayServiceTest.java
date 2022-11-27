package com.ravipas.statsService.services;
//see @Testcontainers as an alternative

import com.ravipas.statsService.StatsServiceApplication;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StatsServiceApplication.class)
public class PlayServiceTest {
    @Autowired
    StatsService statsService;

    @Test
    @DisplayName("Should increment tie scores from a non existing player")
    public void incrementTiesNonExistingUserTest() {
        this.statsService.incrementUserTies("userIdTiesNonExisting");
        Assert.assertEquals(Integer.valueOf(1),this.statsService.getStatsForUser("userIdTiesNonExisting").getTieCount());
    }

    @Test
    @DisplayName("Should increment win scores from a non existing player")
    public void incrementWinningNonExistingUserTest() {
        this.statsService.incrementUserWins("userIdWinsNonExisting");
        Assert.assertEquals(Integer.valueOf(1),this.statsService.getStatsForUser("userIdWinsNonExisting").getWinCount());
    }

    @Test
    @DisplayName("Should increment lose scores from a non existing player")
    public void incrementLosesNonExistingUserTest() {
        this.statsService.incrementUserWins("userIdLoseNonExisting");
        Assert.assertEquals(Integer.valueOf(1),this.statsService.getStatsForUser("userIdLoseNonExisting").getWinCount());
    }

    @Test
    @DisplayName("Should increment tie scores from an existing player")
    public void incrementTiesUserTest() {
        this.statsService.incrementUserTies("userIdTies");
        this.statsService.incrementUserTies("userIdTies");
        Assert.assertEquals(Integer.valueOf(2),this.statsService.getStatsForUser("userIdTies").getTieCount());
    }

    @Test
    @DisplayName("Should increment win scores from an existing player")
    public void incrementWinningUserTest() {
        this.statsService.incrementUserWins("userIdWins");
        this.statsService.incrementUserWins("userIdWins");
        Assert.assertEquals(Integer.valueOf(2),this.statsService.getStatsForUser("userIdWins").getWinCount());
    }

    @Test
    @DisplayName("Should increment lose scores from an existing player")
    public void incrementLosesUserTest() {
        this.statsService.incrementUserLoses("userIdLose");
        this.statsService.incrementUserLoses("userIdLose");
        Assert.assertEquals(Integer.valueOf(2),this.statsService.getStatsForUser("userIdLose").getLoseCount());
    }

}
