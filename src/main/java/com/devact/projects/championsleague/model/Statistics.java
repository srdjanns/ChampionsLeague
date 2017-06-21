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
public class Statistics extends LeagueStats {

    @Column(name = "cl_group", unique = true)
    protected String group;

    @Id
    @Column(name = "statistics_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ElementCollection
    @OrderBy("points, goals, goalDifference")
    private List<Standings> standings;

    public Statistics(StatisticsDto statisticsDto) {
        this.leagueTitle = statisticsDto.getLeagueTitle();
        this.matchday = statisticsDto.getMatchday();
        this.group = statisticsDto.getGroup();
        this.standings = statisticsDto.getStanding().stream().map(standing -> new Standings(standing))
                .collect(Collectors.toList());
    }
}
