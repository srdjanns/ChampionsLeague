package com.devact.projects.championsleague.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "league_title")
    protected String leagueTitle;

    @Column(name = "matchday")
    protected int matchday;
}