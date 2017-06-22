package com.devact.projects.championsleague.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class LeagueStats {

    @Column(name = "league_title", nullable = false)
    protected String leagueTitle;

    @Column(name = "matchday", nullable = false)
    protected int matchday;
}