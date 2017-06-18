package com.devact.projects.championsleague.dto;

import com.devact.projects.championsleague.model.Statistics;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@NoArgsConstructor
public class StatisticsDto extends LeagueStatsDto {

    private List<StandingsDto> standings;

    public StatisticsDto(Statistics statistics) {
        this.leagueTitle = statistics.getLeagueTitle();
        this.matchday = statistics.getMatchday();
        this.group = statistics.getGroup();
        this.standings = statistics.getStandings()
                .stream()
                .map(standings -> new StandingsDto(standings))
                .collect(Collectors.toList());
    }

}
