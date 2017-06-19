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
public class Statistics {

    @Column(name = "league_title")
    protected String leagueTitle;

    @Column(name = "matchday")
    protected int matchday;

    @Column(name = "cl_group", unique = true)
    protected String group;

    @Id
    @Column(name = "statistics_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("rank, goals, goalDifference")
    private List<Standings> standings;

    public Statistics(StatisticsDto statisticsDto) {
        this.leagueTitle = statisticsDto.getLeagueTitle();
        this.matchday = statisticsDto.getMatchday();
        this.group = statisticsDto.getGroup();
        this.standings = statisticsDto.getStanding().stream().map(standing -> new Standings(standing))
                .collect(Collectors.toList());
    }
}
