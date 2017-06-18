package com.devact.projects.championsleague.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode()
@MappedSuperclass
public class LeagueStats {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    protected String leagueTitle;

    @Column
    protected int matchday;

}