package com.devact.projects.championsleague.controller;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

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
    public List<MatchDto> getResults(@RequestParam(value = "dateFrom", required = false) String dateFrom,
            @RequestParam(value = "dateTo", required = false) String dateTo,
            @RequestParam(value = "group", required = false) String group,
            @RequestParam(value = "team", required = false) String team) throws ParseException{
        return matchService.findMatchesByFilters(dateFrom, dateTo, group, team);
    }

    @PostMapping
    @ResponseBody
    public List<StatisticsDto> addMatchesAndReturnNewTable(@NotNull @Valid @RequestBody List<MatchDto> matches) {
        return matchService.addMatchesAndReturnNewTable(matches);
    }
}
