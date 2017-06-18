package com.devact.projects.championsleague.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Srdjan Simidzija
 */
@Getter
@Setter
@EqualsAndHashCode()
@Entity
@Table(name = "match")
public class Match extends LeagueStats{

    @Column
    private String homeTeam;

    @Column
    private String awayTeam;

    @Column
    private Date kickoffat;

    @Column
    private String score;

    @Column(name = "cl_group")
    protected String group;
}
