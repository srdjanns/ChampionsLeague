package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.ChampionsLeagueApplication;
import com.devact.projects.championsleague.model.Match;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Srdjan Simidzija
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChampionsLeagueApplication.class})
@TestPropertySource(locations = "classpath:application.test.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class MatchRepositoryTest {

    public static DateFormat dateFormatter;

    @Autowired
    private MatchRepository matchRepository;

    @Before
    public void setUp() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    @Test
    public void whenAddNewMatch_thenReturnStatistics() throws ParseException {
        // given
        Match match = createDefaultMatch();

        // when
        Match insertedMatch = matchRepository.save(match);

        // then
        assertEquals(match, insertedMatch);
    }

    @Test
    public void whenAddNewMatchWithEmptyGroup_thenThrowBadRequestError() throws ParseException {
        // given
        Match match = createDefaultMatch();
        match.setGroup(null);

        try{
            matchRepository.save(match);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
        }
    }

    @Test
    public void whenFindAllMatches_thenReturnListOfMatches() throws ParseException {
        // given
        Match matchOne = createDefaultMatch();

        Match matchTwo = createDefaultMatch();
        matchTwo.setHomeTeam("Arsenal");
        matchTwo.setAwayTeam("Partizan");

        // when
        List<Match> listOfMatches = matchRepository.save(Arrays.asList(matchOne, matchTwo));

        //then
        assertEquals(2, listOfMatches.size());
    }

    @Test
    public void whenDeleteMatch_thenReturnResultWithoutDeletedMatch() throws ParseException {
        // given
        Match matchOne = createDefaultMatch();

        Match matchTwo = createDefaultMatch();
        matchTwo.setHomeTeam("Arsenal");
        matchTwo.setAwayTeam("Partizan");

        List<Match> listOfMatches = matchRepository.save(Arrays.asList(matchOne, matchTwo));

        // when
        matchRepository.delete(matchOne);

        // then
        List<Match> listOfAllMatches = matchRepository.findAll();
        assertNotEquals(listOfAllMatches, listOfMatches);
    }

    private static Match createMatch(String league, String group, String homeTeam, String awayTeam, String score)
            throws ParseException {
        Match match = new Match();
        match.setLeagueTitle(league);
        match.setGroup(group);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setScore(score);
        match.setMatchday(1);
        match.setKickoffat(dateFormatter.parse("2017-06-15T20:45:00"));
        return match;
    }

    private static Match createDefaultMatch() throws ParseException {
        return createMatch("Champions League", "A", "Manchester United", "Eastwitch",
                "0:3");
    }

}
