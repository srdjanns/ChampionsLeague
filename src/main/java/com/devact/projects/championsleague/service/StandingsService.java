package com.devact.projects.championsleague.service;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.model.Standings;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.StandingsRepository;
import com.devact.projects.championsleague.repository.StatisticsRepository;

/**
 * @author Srdjan Simidzija
 */

@Service
public class StandingsService {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private StandingsRepository standingsRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    public Standings findStandingsByTeam(String team) {
        return standingsRepository.findByTeam(team);
    }

    public void updateStandings(Statistics statistics, MatchDto match) {
        Standings homeTeamStandings = findStandingsByTeam(match.getHomeTeam());
        Standings awayTeamStandings = findStandingsByTeam(match.getAwayTeam());

        calculateScoreAndAssignPoints(homeTeamStandings, awayTeamStandings, match.getScore());

        statistics.setStandings(statistics.getStandings().stream().map(s -> {
            if (s.getTeam().equals(homeTeamStandings)) {
                return homeTeamStandings;
            } else if (s.getTeam().equals(awayTeamStandings)) {
                return awayTeamStandings;
            } else {
                return s;
            }
        }).collect(Collectors.toList()));
    }

    private void calculateScoreAndAssignPoints(Standings firstTeam, Standings secondTeam, final String score) {
        int firstTeamGoals;
        int secondTeamGoals;

        String[] goals = score.split(":");
        firstTeamGoals = Integer.valueOf(goals[0]);
        secondTeamGoals = Integer.valueOf(goals[1]);
        if (firstTeamGoals > secondTeamGoals) {
            // add 3 points to first team and increment number of won matches
            firstTeam.setPoints(firstTeam.getPoints() + 3);
            firstTeam.setWin(firstTeam.getWin() + 1);
            // increment number of lost matches to second team
            secondTeam.setLose(secondTeam.getLose() + 1);
        } else if (firstTeamGoals < secondTeamGoals) {
            // add 3 points to second team and increment number of won matches
            secondTeam.setPoints(secondTeam.getPoints() + 3);
            secondTeam.setWin(secondTeam.getWin() + 1);
            // increment number of lost matches to first team
            firstTeam.setLose(firstTeam.getLose() + 1);
        } else {
            // assign both teams one point
            firstTeam.setPoints(firstTeam.getPoints() + 1);
            firstTeam.setDraw(firstTeam.getDraw() + 1);
            // increment number of draw matches to both teams
            secondTeam.setPoints(secondTeam.getPoints() + 1);
            secondTeam.setDraw(secondTeam.getDraw() + 1);
        }
        setAdditionalData(firstTeam, secondTeam, firstTeamGoals, secondTeamGoals);

    }

    private void setAdditionalData(Standings firstTeam, Standings secondTeam, int firstTeamGoals, int secondTeamGoals) {
        // set other data for first team
        firstTeam.setPlayedGames(firstTeam.getPlayedGames() + 1);
        firstTeam.setGoals(firstTeam.getGoals() + firstTeamGoals);
        firstTeam.setGoalsAgainst(firstTeam.getGoalsAgainst() + secondTeamGoals);
        firstTeam.setGoalDifference(firstTeam.getGoals() - firstTeam.getGoalsAgainst());

        // set other data for second team
        secondTeam.setPlayedGames(secondTeam.getPlayedGames() + 1);
        secondTeam.setGoals(secondTeam.getGoals() + secondTeamGoals);
        secondTeam.setGoalsAgainst(secondTeam.getGoalsAgainst() + firstTeamGoals);
        secondTeam.setGoalDifference(secondTeam.getGoals() - secondTeam.getGoalsAgainst());
    }
}
