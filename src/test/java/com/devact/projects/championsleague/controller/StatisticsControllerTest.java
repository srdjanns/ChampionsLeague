package com.devact.projects.championsleague.controller;

import com.devact.projects.championsleague.ChampionsLeagueApplication;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Standings;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.StatisticsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Srdjan Simidzija
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChampionsLeagueApplication.class})
@AutoConfigureMockMvc

public class StatisticsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsRepository statisticsRepository;

    private RestTemplate restTemplate;


    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }


    @Test
    public void givenGroup_whenGetStatisticsForGroup_thenReturnStatistics() throws Exception {
        // given
        String group = "A";
        Statistics s = createDefaultStatistics();
        statisticsRepository.save(s);

        ResponseEntity<StatisticsDto> response = restTemplate.getForEntity("http://localhost:8080/statistics/" + group,
                StatisticsDto.class);

        // then
        assertEquals(s.getGroup(), response.getBody().getGroup());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private Statistics createDefaultStatistics() {
        Statistics statistics = new Statistics();
        statistics.setGroup("A");

        statistics.setStandings(Arrays.asList(createDefaultStandings()));
        return statistics;
    }

    private Standings createDefaultStandings() {
        Standings standings = new Standings();
        standings.setTeam("Arsenal");
        standings.setGoals(4);
        standings.setDraw(0);
        standings.setGoalDifference(-3);
        standings.setGoalsAgainst(1);
        standings.setLose(0);
        standings.setPlayedGames(2);
        standings.setPoints(6);
        standings.setRank(1);
        standings.setWin(2);
        return standings;
    }
}