package com.ravipas.statsService.integration;

import com.ravipas.statsService.StatsServiceApplication;
import com.ravipas.statsService.dto.StatsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = StatsServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatsServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHitWinnigEndpoint() {
        //TODO: check for alternatives fr this seems like patch is not available
        // options seems to get along with overriding supported methods
        // https://medium.com/javarevisited/invalid-http-method-patch-e12ba62ddd9f

        /*this.restTemplate
                        .patchForObject("http://localhost:" + port + "/win/test_user", null,UserScore.class);*/

        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/stats/top", StatsDTO[].class).length== 0);
    }

    //TODO: implement rest on integration tests hitting diferent endpoint methods and asserting for changes in domain data
}
