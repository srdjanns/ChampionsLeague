package com.devact.projects.championsleague.model;

import com.devact.projects.championsleague.dto.StandingsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "standings")
public class Standings{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rank", nullable = false)
    protected int rank;

    @Column(name = "team", nullable = false)
    protected String team;

    @Column(name = "played_games", nullable = false)
    protected int playedGames;

    @Column(name = "points", nullable = false)
    protected int points;

    @Column(name = "goals", nullable = false)
    protected int goals;

    @Column(name = "goals_against", nullable = false)
    protected int goalsAgainst;

    @Column(name = "goal_difference", nullable = false)
    protected int goalDifference;

    @Column(name = "win", nullable = false)
    protected int win;

    @Column(name = "lose", nullable = false)
    protected int lose;

    @Column(name = "draw", nullable = false)
    protected int draw;

    public Standings(StandingsDto standingsDto) {
        this.rank = standingsDto.getRank();
        this.team = standingsDto.getTeam();
        this.playedGames = Integer.valueOf(standingsDto.getPlayedGames());
        this.points = Integer.valueOf(standingsDto.getPoints());
        this.goals = Integer.valueOf(standingsDto.getGoals());
        this.goalsAgainst = Integer.valueOf(standingsDto.getGoalsAgainst());
        this.goalDifference = Integer.valueOf(standingsDto.getGoalDifference());
        this.win = Integer.valueOf(standingsDto.getWin());
        this.lose = Integer.valueOf(standingsDto.getLose());
        this.draw = Integer.valueOf(standingsDto.getDraw());
    }
}
