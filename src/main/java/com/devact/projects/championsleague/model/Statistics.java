package com.devact.projects.championsleague.model;

import com.devact.projects.championsleague.dto.StatisticsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column
    @OneToMany
    private List<Standings> standings;

    public Statistics(StatisticsDto statisticsDto) {
        this.leagueTitle = statisticsDto.getLeagueTitle();
        this.matchday = statisticsDto.getMatchday();
        this.group = statisticsDto.getGroup();
        this.standings = statisticsDto.getStandings().stream()
                .map(standings -> new Standings(standings))
                .collect(Collectors.toList());
    }
}
