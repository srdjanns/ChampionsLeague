package com.devact.projects.championsleague.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class LeagueStatsDto {

    @NotNull
    protected String leagueTitle;

    @NotNull
    protected int matchday;

    @NotNull
    protected String group;
}
