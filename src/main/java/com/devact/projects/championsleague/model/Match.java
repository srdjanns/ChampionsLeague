package com.devact.projects.championsleague.model;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.utils.DateTimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date kickoffat;

    @Column(name = "score")
    private String score;

    public Match(MatchDto matchDto) throws ParseException {
        this.leagueTitle = matchDto.getLeagueTitle();
        this.matchday = matchDto.getMatchday();
        this.score = matchDto.getScore();
        this.homeTeam = matchDto.getHomeTeam();
        this.awayTeam = matchDto.getAwayTeam();
        this.kickoffat = DateTimeUtils.dateFormatter.parse(matchDto.getKickoffAt());
        this.score = matchDto.getScore();
        this.group = matchDto.getGroup();
    }
}
