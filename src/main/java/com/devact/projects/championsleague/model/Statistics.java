package com.devact.projects.championsleague.model;

import com.devact.projects.championsleague.dto.StatisticsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "statistics")
public class Statistics {

    @Id
    @Column(name = "statistics_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "league_title")
    protected String leagueTitle;

    @Column(name = "matchday")
    protected int matchday;

    @Column(name = "cl_group", unique = true)
    protected String group;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("rank, goals, goalDifference")
    private List<Standings> standings;

    public Statistics(StatisticsDto statisticsDto) {
        this.leagueTitle = statisticsDto.getLeagueTitle();
        this.matchday = statisticsDto.getMatchday();
        this.group = statisticsDto.getGroup();
        this.standings = statisticsDto.getStanding().stream()
                .map(standing -> new Standings(standing))
                .collect(Collectors.toList());
    }
}
