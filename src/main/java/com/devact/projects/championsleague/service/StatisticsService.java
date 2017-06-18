package com.devact.projects.championsleague.service;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srdjan Simidzija
 */

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<StatisticsDto> findAllStatistics() {
        return statisticsRepository.findAll()
                .stream()
                .map(statistics -> new StatisticsDto(statistics))
                .collect(Collectors.toList());
    }
}
