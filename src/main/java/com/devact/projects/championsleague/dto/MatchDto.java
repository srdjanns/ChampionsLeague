package com.devact.projects.championsleague.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Srdjan Simidzija
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MatchDto extends LeagueStatsDto {

    @NotNull
    private String homeTeam;

    @NotNull
    private String awayTeam;

    @NotNull
    private Date kickoffat;

    @NotNull
    private String score;
}
