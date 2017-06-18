package com.devact.projects.championsleague.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Srdjan Simidzija
 */
@Getter
@Setter
@EqualsAndHashCode()
@Entity
@Table(name = "standings")
public class Standings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    protected int rank;

    @Column
    protected String team;

    @Column()
    protected int playedGames;

    @Column()
    protected int points;

    @Column()
    protected int goals;

    @Column()
    protected int goalsAgainst;

    @Column()
    protected int goalDifference;

    @Column()
    protected int win;

    @Column()
    protected int lose;

    @Column()
    protected int draw;
}
