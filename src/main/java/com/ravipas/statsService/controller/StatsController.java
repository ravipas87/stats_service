package com.ravipas.statsService.controller;

import com.ravipas.statsService.dto.StatsDTO;
import com.ravipas.statsService.models.UserScore;
import com.ravipas.statsService.services.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StatsController {

    @Autowired
    StatsService statsService;

    @Operation(summary = "Get the stats of a given user by it's id")
    @GetMapping("/stats/users/{id}")
    public StatsDTO getStatsForUser(@PathVariable String id) {
        return this.statsService.getStatsForUser(id);
    }

    @Operation(summary = "Get the top stats, passing the number ")
    @GetMapping("/stats/top") //make it optional
    public List<StatsDTO> getTopTenStats() {
        return this.statsService.getTopStats(10);
    }

    @Operation(summary = "Get the n top stats ")
    @GetMapping("/stats/top/{limit}")
    public List<StatsDTO> getTopStats(@PathVariable Integer limit) {
        return this.statsService.getTopStats(limit);
    }

    //operations to hide behind API GATEWAY
    // PATCH is not required to be idempotent by RESTFull philosophy
    @Operation(summary = "Increment user win counter")
    @PatchMapping("/win/{userId}")
    public UserScore incrementWinCounter(@PathVariable String userId) {
        log.info("hitting win endpoint for user " +userId);
        return this.statsService.incrementUserWins(userId);
    }

    @Operation(summary = "Increment user lose counter")
    @PatchMapping("/lose/{userId}")
    public UserScore incrementLoseCounter(@PathVariable String userId) {
        log.info("hitting lose endpoint for user " +userId);
        return this.statsService.incrementUserLoses(userId);
    }

    @Operation(summary = "Increment user tie counter")
    @PatchMapping("/tie/{userId}")
    public UserScore incrementTieCounter(@PathVariable String userId) {
        log.info("hitting tie endpoint for user " +userId);
        return this.statsService.incrementUserTies(userId);
    }
}
