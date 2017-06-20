package com.devact.projects.championsleague.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devact.projects.championsleague.dto.StandingsDto;
import com.devact.projects.championsleague.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.MatchRepository;

/**
 * @author Srdjan Simidzija
 */

@Service
public class MatchService {

    public static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StandingsService standingsService;

    public List<MatchDto> findAllMatches() {
        return matchRepository.findAll().stream().map(match -> new MatchDto(match)).collect(Collectors.toList());
    }

    public List<StandingsDto> findMatchesByFilters(Date dateFrom, Date dateTo, String group, String team) {
        logger.info("Searching for matches with ");
        return new ArrayList<StandingsDto>();
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
        for (MatchDto matchDto : matchesDto) {
            matches.add(new Match(matchDto));
        }
        matchRepository.save(matches);
    }

    private List<String> populateGroups(final List<MatchDto> matches) {
        return matches.stream().map(match -> match.getGroup()).distinct().collect(Collectors.toList());
    }
}
