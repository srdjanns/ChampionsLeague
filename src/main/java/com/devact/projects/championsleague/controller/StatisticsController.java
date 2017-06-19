package com.devact.projects.championsleague.controller;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Srdjan Simidzija
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    @ResponseBody
    public List<StatisticsDto> getStatistics() {
        return statisticsService.findAllStatistics();
    }

    @GetMapping("/{group}")
    @ResponseBody
    public StatisticsDto getStatisticsForGroup(@PathVariable String group) {
        return statisticsService.findStatisticsByGroup(group);
    }
}
