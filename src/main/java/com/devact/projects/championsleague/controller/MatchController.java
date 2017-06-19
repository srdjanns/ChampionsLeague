package com.devact.projects.championsleague.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.MatchService;

/**
 * @author Srdjan Simidzija
 *
 * Controller class for handling <code>/matches</code> mapped REST requests
 */
@RestController
@RequestMapping("/matches")
public class MatchController {

    public static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<MatchDto> getMatches() {
        return matchService.findAllMatches();
    }

    @PostMapping
    public StatisticsDto addMatchesAndReturnNewTable(@Valid @RequestBody List<MatchDto> matches) {
        return matchService.addMatchesAndReturnNewTable(matches);
    }
}
