package com.devact.projects.championsleague.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class LeagueStatsDto {


    @NotNull
    protected String leagueTitle;

    @NotNull
    protected int matchday;

    @NotNull
    protected String group;
}
