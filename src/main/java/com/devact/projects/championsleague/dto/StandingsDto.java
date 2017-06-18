package com.devact.projects.championsleague.dto;

import com.devact.projects.championsleague.model.Standings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@NoArgsConstructor
public class StandingsDto {

    @NonNull
    private int rank;

    @NonNull
    private String team;

    @NonNull
    private String playedGames;

    @NonNull
    private String points;

    @NonNull
    private String goals;

    @NonNull
    private String goalsAgainst;

    @NonNull
    private String goalDifference;

    @NonNull
    private String win;

    @NonNull
    private String lose;

    @NonNull
    private String draw;

    public StandingsDto(Standings standings) {
        this.rank = standings.getRank();
        this.team = standings.getTeam();
        this.playedGames = String.valueOf(standings.getPlayedGames());
        this.points = String.valueOf(standings.getPoints());
        this.goals = String.valueOf(standings.getGoals());
        this.goalsAgainst = String.valueOf(standings.getGoalsAgainst());
        this.goalDifference = String.valueOf(standings.getGoalDifference());
        this.win = String.valueOf(standings.getWin());
        this.lose = String.valueOf(standings.getLose());
        this.draw = String.valueOf(standings.getDraw());
    }

}
