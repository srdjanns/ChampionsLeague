package com.devact.projects.championsleague.controller;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Srdjan Simidzija
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    public static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public List<StatisticsDto> getStatistics() {
        return statisticsService.findAllStatistics();
    }
}
