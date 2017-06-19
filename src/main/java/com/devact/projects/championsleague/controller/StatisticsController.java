package com.devact.projects.championsleague.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.StatisticsService;

/**
 * @author Srdjan Simidzija
 *
 * Controller class for handling <code>/statistics</code> mapped REST requests
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public List<StatisticsDto> getStatistics() {
        return statisticsService.findAllStatistics();
    }
}
