package com.devact.projects.championsleague.dto;

import com.devact.projects.championsleague.model.Match;
import com.devact.projects.championsleague.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto extends LeagueStatsDto {

    @NotNull
    private String homeTeam;

    @NotNull
    private String awayTeam;

    @NotNull
    private String kickoffAt;

    @NotNull
    private String score;

    public MatchDto(Match match) {
        this.leagueTitle = match.getLeagueTitle();
        this.matchday = match.getMatchday();
        this.score = match.getScore();
        this.homeTeam = match.getHomeTeam();
        this.awayTeam = match.getAwayTeam();
        this.kickoffAt = DateTimeUtils.dateFormatter.format(match.getKickoffat());
        this.score = match.getScore();
        this.group = match.getGroup();
    }

}
