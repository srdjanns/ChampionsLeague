package com.devact.projects.championsleague.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<StatisticsDto> addMatchesAndReturnNewTable(List<MatchDto> matches) {
        logger.info("Adding new matches and returning updated statistics table...");
        List<StatisticsDto> result = new ArrayList<>();
        List<String> groups = populateGroups(matches);
        List<StatisticsDto> statisticsList = new ArrayList<>();
        for (String group : groups) {
            statisticsList.add(statisticsService.findStatisticsByGroup(group));
        }
        for (StatisticsDto statistics : statisticsList) {
            for (MatchDto match : matches) {
                if (match.getGroup().equals(statistics.getGroup())) {
                    updateStatisticsAndAddToList(result, match);
                    break;
                }
            }
        }
        return result;
    }

    private boolean updateStatisticsAndAddToList(final List<StatisticsDto> result, final MatchDto match) {
        String group = match.getGroup();
        Statistics statistics = new Statistics(statisticsService.findStatisticsByGroup(group));
        if (statistics == null) {
            logger.error("There are no statistics for group " + group);
            return true;
        }
        standingsService.updateStandings(statistics, match);
        statisticsService.updateStatistics(statistics);
        result.add(statisticsService.findStatisticsByGroup(group));
        return false;
    }

    private List<String> populateGroups(final List<MatchDto> matches) {
        return matches.stream().map(match -> match.getGroup()).distinct().collect(Collectors.toList());
    }
}
