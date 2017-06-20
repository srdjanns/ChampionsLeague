package com.devact.projects.championsleague.model;

import java.util.Date;

import javax.persistence.*;

import com.devact.projects.championsleague.dto.MatchDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "match")
public class Match extends LeagueStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cl_group")
    protected String group;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "away_team")
    private String awayTeam;

    @Column(name = "kickoff_at")
    private Date kickoffat;

    @Column(name = "score")
    private String score;

    public Match(MatchDto matchDto) {
        this.leagueTitle = matchDto.getLeagueTitle();
        this.matchday = matchDto.getMatchday();
        this.score = matchDto.getScore();
        this.homeTeam = matchDto.getHomeTeam();
        this.awayTeam = matchDto.getAwayTeam();
        this.kickoffat = matchDto.getKickoffAt();
        this.score = matchDto.getScore();
        this.group = matchDto.getGroup();
    }
}
