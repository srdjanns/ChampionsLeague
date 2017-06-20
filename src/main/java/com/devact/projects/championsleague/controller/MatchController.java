package com.devact.projects.championsleague.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseBody
    public List<MatchDto> getResults(@RequestParam(value = "dateFrom", required = false) Date dateFrom,
            @RequestParam(value = "dateTo", required = false) Date dateTo,
            @RequestParam(value = "group", required = false) String group,
            @RequestParam(value = "team", required = false) String team) {
        return matchService.findMatchesByFilters(dateFrom, dateTo, group, team);
    }

    @PostMapping
    @ResponseBody
    public List<StatisticsDto> addMatchesAndReturnNewTable(@NotNull @Valid @RequestBody List<MatchDto> matches) {
        return matchService.addMatchesAndReturnNewTable(matches);
    }
}
