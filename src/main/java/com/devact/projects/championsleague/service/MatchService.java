package com.devact.projects.championsleague.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Match;
import com.devact.projects.championsleague.model.QMatch;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.MatchRepository;

/**
 * @author Srdjan Simidzija
 */

@Service
public class MatchService {

    public static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StandingsService standingsService;

    public List<MatchDto> findAllMatches() {
        return matchRepository.findAll().stream().map(match -> new MatchDto(match)).collect(Collectors.toList());
    }

    public List<MatchDto> findMatchesByFilters(Date dateFrom, Date dateTo, String group, String team) {
        logger.info("Searching for matches with filters: " + dateFrom + " " + dateTo + " " + group + " " + team);
        if (dateFrom == null && dateTo == null && group == null && team == null) {
            return convertMatchesIntoMatchesDto(matchRepository.findAll());
        }
        return convertMatchesIntoMatchesDto(matchRepository.findAll(QMatch.match.group.contains(ifNotNull(group))
                .and(QMatch.match.homeTeam.contains(ifNotNull(team)).or(QMatch.match.awayTeam.contains(ifNotNull(team))))));
//                .and(QMatch.match.kickoffat.between(ifNotNullDateFrom(dateFrom), ifNotNull(dateTo)))));
    }

    public List<StatisticsDto> addMatchesAndReturnNewTable(List<MatchDto> matchesDto) {
        logger.info("Adding new matches and returning updated statistics table...");
        insertMatches(matchesDto);
        List<StatisticsDto> result = new ArrayList<>();
        List<String> groups = populateGroups(matchesDto);
        List<StatisticsDto> statisticsList = new ArrayList<>();
        for (String group : groups) {
            statisticsList.add(statisticsService.findStatisticsByGroup(group));
        }
        for (StatisticsDto statistics : statisticsList) {
            for (MatchDto match : matchesDto) {
                if (match.getGroup().equals(statistics.getGroup())) {
                    updateStatisticsAndAddToList(result, match);
                    break;
                }
            }
        }
        return result;
    }

    private void updateStatisticsAndAddToList(final List<StatisticsDto> result, final MatchDto match) {
        String group = match.getGroup();
        Statistics statistics = new Statistics(statisticsService.findStatisticsByGroup(group));
        if (statistics == null) {
            logger.error("There are no statistics for group " + group);
            return;
        }
        standingsService.updateStandings(statistics, match);
        statisticsService.updateStatistics(statistics);
        result.add(statisticsService.findStatisticsByGroup(group));
        return;
    }

    private void insertMatches(final List<MatchDto> matchesDto) {
        List<Match> matches = new ArrayList<>();
        matches = matchesDto.stream().map(match -> new Match(match)).collect(Collectors.toList());
        matchRepository.save(matches);
    }

    private List<MatchDto> convertMatchesIntoMatchesDto(List<Match> matches) {
        return matches.stream().map(match -> new MatchDto(match)).collect(Collectors.toList());
    }

    private List<String> populateGroups(final List<MatchDto> matches) {
        return matches.stream().map(match -> match.getGroup()).distinct().collect(Collectors.toList());
    }

    private String ifNotNull(String parameter) {
        if (parameter == null) {
            return "";
        }
        return parameter;
    }

    private Date ifNotNullDateFrom(Date parameter) {
        if (parameter == null) {
            return new Date(new Date().getTime() - (365 * 24 * 60 * 60 * 1000));
        }
        return parameter;
    }

    private Date ifNotNull(Date parameter) {
        if (parameter == null) {
            return new Date();
        }
        return parameter;
    }
}
