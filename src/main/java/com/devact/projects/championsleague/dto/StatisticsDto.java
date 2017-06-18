package com.devact.projects.championsleague.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by Srdjan on 17/06/17.
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class StatisticsDto extends LeagueStatsDto {

    private List<StandingsDto> standings;
}
