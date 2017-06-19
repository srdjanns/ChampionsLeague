package com.devact.projects.championsleague.model;

import javax.persistence.*;

import com.devact.projects.championsleague.dto.StandingsDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Srdjan Simidzija
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "standings")
public class Standings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rank")
    protected int rank;

    @Column(name = "team", unique = true)
    protected String team;

    @Column(name = "played_games")
    protected int playedGames;

    @Column(name = "points")
    protected int points;

    @Column(name = "goals")
    protected int goals;

    @Column(name = "goals_against")
    protected int goalsAgainst;

    @Column(name = "goal_difference")
    protected int goalDifference;

    @Column(name = "win")
    protected int win;

    @Column(name = "lose")
    protected int lose;

    @Column(name = "draw")
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
