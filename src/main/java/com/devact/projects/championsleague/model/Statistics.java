package com.devact.projects.championsleague.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.devact.projects.championsleague.dto.StatisticsDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "statistics")
public class Statistics extends LeagueStats {

    @Column(name = "cl_group", unique = true)
    protected String group;

    @Column
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("points, goals, goalDifference")
    private List<Standings> standings;

    public Statistics(StatisticsDto statisticsDto) {
        this.leagueTitle = statisticsDto.getLeagueTitle();
        this.matchday = statisticsDto.getMatchday();
        this.group = statisticsDto.getGroup();
        this.standings = statisticsDto
                        .getStanding()
                        .stream()
                        .map(standing -> new Standings(standing))
                        .collect(Collectors.toList());
    }
}
