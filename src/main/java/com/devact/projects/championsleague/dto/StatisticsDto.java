package com.devact.projects.championsleague.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.devact.projects.championsleague.model.Statistics;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsDto extends LeagueStatsDto {

    @JsonIgnore
    private long id;

    private List<StandingsDto> standing;

    public StatisticsDto(Statistics statistics) {
        this.id = statistics.getId();
        this.leagueTitle = statistics.getLeagueTitle();
        this.matchday = statistics.getMatchday();
        this.group = statistics.getGroup();
        this.standing = statistics.getStandings().stream().map(standings -> new StandingsDto(standings))
                .collect(Collectors.toList());
    }

}
