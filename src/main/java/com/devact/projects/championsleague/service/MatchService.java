package com.devact.projects.championsleague.service;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Match;
import com.devact.projects.championsleague.model.QMatch;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.MatchRepository;
import com.devact.projects.championsleague.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.devact.projects.championsleague.utils.MatchUtils.*;

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

    public List<MatchDto> findMatchesByFilters(String dateFrom, String dateTo, String group, String team) throws ParseException{
        logger.info("Searching for matches with filters: " + dateFrom + " " + dateTo + " " + group + " " + team);
        if (dateFrom == null && dateTo == null && group == null && team == null) {
            return convertMatchesIntoMatchesDto(matchRepository.findAll());
        }
        // create query criteria; for values that are null, replace them with empty string
        return convertMatchesIntoMatchesDto(getFilteredMatches(dateFrom, dateTo, group, team));
    }

    public List<StatisticsDto> addMatchesAndReturnNewTable(List<MatchDto> matchesDto) {
        logger.info("Adding new matches and returning updated statistics table...");
        insertMatches(matchRepository, matchesDto);
        List<StatisticsDto> result = new ArrayList<>();
        List<String> groups = populateGroups(matchesDto);
        List<StatisticsDto> statisticsList = new ArrayList<>();
        for (String group : groups) {
            statisticsList.add(statisticsService.findStatisticsByGroup(group));
        }
        for (StatisticsDto statistics : statisticsList) {
            for (MatchDto match : matchesDto) {
                if (match.getGroup().equals(statistics.getGroup())) {
                    updateStatisticsAndAddToList(result, match);
                    break;
                }
            }
        }
        return result;
    }

    private void updateStatisticsAndAddToList(final List<StatisticsDto> result, final MatchDto match) {
        String group = match.getGroup();
        Statistics statistics = new Statistics(statisticsService.findStatisticsByGroup(group));
        if (statistics == null) {
            logger.error("There are no statistics for group " + group);
            return;
        }
        standingsService.updateStandings(statistics, match);
        result.add(statisticsService.findStatisticsByGroup(group));
        return;
    }

    private List<Match> getFilteredMatches(String dateFrom, String dateTo, String group, String team) throws ParseException {
        return matchRepository.findAll(
                QMatch.match.group.contains(ifNotNull(group))
                        .and(QMatch.match.homeTeam.contains(ifNotNull(team)).or(QMatch.match.awayTeam.contains(ifNotNull(team))))
                        .and(QMatch.match.kickoffat.between(ifNotNullDateFrom(dateFrom), ifNotNullDateTo(dateTo))));
    }
}
