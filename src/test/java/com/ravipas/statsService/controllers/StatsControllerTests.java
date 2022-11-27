package com.ravipas.statsService.controllers;

import com.ravipas.statsService.controller.StatsController;
import com.ravipas.statsService.dto.StatsDTO;
import com.ravipas.statsService.models.UserScore;
import com.ravipas.statsService.persistence.IStatsRepository;
import com.ravipas.statsService.services.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

// web layer testing
@WebMvcTest(StatsController.class)
@ExtendWith(SpringExtension.class)

public class StatsControllerTests {
    @MockBean
    StatsService statsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldGetUserScores() throws Exception {
        Mockito.when(this.statsService.getStatsForUser("aef12")).thenReturn(new StatsDTO(new UserScore("aef12") ));
        mockMvc.perform(get("/stats/users/aef12")).andExpect(status().isOk()).andExpect(content().string(containsString("{\"winCount\":0,\"loseCount\":0,\"tieCount\":0}")));
    }

    @Test
    public void shouldGetTopScores() throws Exception {
        Mockito.when(this.statsService.getTopStats(2)).thenReturn(List.of( new StatsDTO(new UserScore("aef12")),new StatsDTO(new UserScore("b6724"))));
        mockMvc.perform(get("/stats/top/2")).andExpect(status().isOk()).andExpect(content().string(containsString("[{\"winCount\":0,\"loseCount\":0,\"tieCount\":0},{\"winCount\":0,\"loseCount\":0,\"tieCount\":0}]")));
    }

    @Test
    public void shouldGetTopTenUserScores() throws Exception {
        Mockito.when(this.statsService.getTopStats(10)).thenReturn(List.of( new StatsDTO(new UserScore("aef12")),new StatsDTO(new UserScore("b6724"))));
        mockMvc.perform(get("/stats/top")).andExpect(status().isOk()).andExpect(content().string(containsString("[{\"winCount\":0,\"loseCount\":0,\"tieCount\":0},{\"winCount\":0,\"loseCount\":0,\"tieCount\":0}]")));

    }

    @Test
    public void incrementWins() throws Exception {
        Mockito.when(this.statsService.incrementUserWins("aef45")).thenReturn(new UserScore(1,"aef45",1,0,0));
        mockMvc.perform(patch("/win/aef45")).andExpect(status().isOk()).andExpect(content().string(containsString("{\"creationDate\":null,\"lastModifiedDate\":null,\"id\":1,\"userId\":\"aef45\",\"winCount\":1,\"loseCount\":0,\"tieCount\":0}")));

    }

    @Test
    public void incrementLoses() throws Exception {
        Mockito.when(this.statsService.incrementUserLoses("aef98")).thenReturn(new UserScore(1,"aef98",0,1,0));
        mockMvc.perform(patch("/lose/aef98")).andExpect(status().isOk()).andExpect(content().string(containsString("{\"creationDate\":null,\"lastModifiedDate\":null,\"id\":1,\"userId\":\"aef98\",\"winCount\":0,\"loseCount\":1,\"tieCount\":0}")));

    }

    @Test
    public void incrementTies() throws Exception {
        Mockito.when(this.statsService.incrementUserTies("aef55")).thenReturn(new UserScore(1,"aef55",0,0,1));
        mockMvc.perform(patch("/tie/aef55")).andExpect(status().isOk()).andExpect(content().string(containsString("{\"creationDate\":null,\"lastModifiedDate\":null,\"id\":1,\"userId\":\"aef55\",\"winCount\":0,\"loseCount\":0,\"tieCount\":1}")));
    }
}
