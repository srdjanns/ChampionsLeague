package com.devact.projects.championsleague.model;

import com.devact.projects.championsleague.dto.MatchDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@Table(name = "match")
public class Match extends LeagueStats{

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "away_team")
    private String awayTeam;

    @Column(name = "kickoff_at")
    private Date kickoffat;

    @Column(name = "score")
    private String score;

    @Column(name = "cl_group")
    protected String group;

    public Match(MatchDto matchDto) {
        this.leagueTitle = matchDto.getLeagueTitle();
        this.matchday = matchDto.getMatchday();
        this.score = matchDto.getScore();
        this.homeTeam = matchDto.getHomeTeam();
        this.awayTeam = matchDto.getAwayTeam();
        this.kickoffat = matchDto.getKickoffat();
        this.score = matchDto.getScore();
    }
}
