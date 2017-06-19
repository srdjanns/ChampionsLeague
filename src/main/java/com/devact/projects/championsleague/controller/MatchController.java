package com.devact.projects.championsleague.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.MatchService;

/**
 * @author Srdjan Simidzija
 */

@RestController
@RequestMapping("/matches")
public class MatchController {

    public static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @PostMapping
    public List<StatisticsDto> addMatchesAndReturnNewTable(@Valid @RequestBody List<MatchDto> matches) {
        logger.info("Adding new matches and returning updated statistics table...");
        return matchService.addMatchesAndReturnNewTable(matches);
    }
}
