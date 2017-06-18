package com.devact.projects.championsleague.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
public class LeagueStatsDto {


    @NotNull
    protected String leagueTitle;

    @NotNull
    protected int matchday;

    @NotNull
    protected String group;
}
