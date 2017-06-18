package com.devact.projects.championsleague.dto;

import com.devact.projects.championsleague.model.Statistics;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class StatisticsDto extends LeagueStatsDto {

    private List<StandingsDto> standing;

    public StatisticsDto(Statistics statistics) {
        this.leagueTitle = statistics.getLeagueTitle();
        this.matchday = statistics.getMatchday();
        this.group = statistics.getGroup();
        this.standing = statistics.getStandings()
                .stream()
                .map(standings -> new StandingsDto(standings))
                .collect(Collectors.toList());
    }

}
