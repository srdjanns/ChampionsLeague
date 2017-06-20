package com.devact.projects.championsleague.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueStatsDto {

    @NotNull
    protected String leagueTitle;

    @NotNull
    protected int matchday;

    @NotNull
    protected String group;
}
