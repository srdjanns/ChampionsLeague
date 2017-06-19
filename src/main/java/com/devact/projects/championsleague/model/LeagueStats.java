package com.devact.projects.championsleague.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class LeagueStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "league_title", unique = true)
    protected String leagueTitle;

    @Column(name = "matchday")
    protected int matchday;
}