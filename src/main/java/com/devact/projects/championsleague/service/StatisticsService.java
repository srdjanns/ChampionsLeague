package com.devact.projects.championsleague.service;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srdjan Simidzija
 */

@Service
public class StatisticsService {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<StatisticsDto> findAllStatistics() {
        logger.info("Looking for all statistics...");
        return statisticsRepository.findAll().stream().map(statistics -> new StatisticsDto(statistics))
                .collect(Collectors.toList());
    }

    public StatisticsDto findStatisticsByGroup(String group) {
        return new StatisticsDto(statisticsRepository.findStatisticsByGroupIgnoreCase(group).get());
    }

    public void updateStatistics(Statistics statistics) {
//        Statistics oldStatistics = statisticsRepository.findStatisticsByGroup(statistics.getGroup()).get();
        statisticsRepository.deleteStatisticsByGroup(statistics.getGroup());
        statisticsRepository.save(statistics);
    }
}
