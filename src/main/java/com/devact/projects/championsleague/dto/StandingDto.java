package com.devact.projects.championsleague.dto;

import lombok.*;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class StandingDto {

    @NonNull
    private int rank;

    @NonNull
    private String ream;

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

}
