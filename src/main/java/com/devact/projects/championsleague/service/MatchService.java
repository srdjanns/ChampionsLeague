package com.devact.projects.championsleague.service;

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

    public StatisticsDto addMatchesAndReturnNewTable(List<MatchDto> matches) {
        // currently, let's assume that only matches from a certain group will be sent
        String group = matches.get(0).getGroup();
        Statistics statistics = statisticsService.findStatisticsByGroup(group);
        if (statistics == null) {
            logger.error("There are no statistics for group " + group);
            return new StatisticsDto();
        }
        for (MatchDto match : matches) {
            standingsService.updateStandings(statistics, match);
        }
        statisticsService.updateStatistics(statistics);
        return new StatisticsDto(statisticsService.findStatisticsByGroup(group));

    }
}
