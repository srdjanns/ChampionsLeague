package com.devact.projects.championsleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devact.projects.championsleague.model.Standings;

/**
 * Created by Srdjan on 18/06/17.
 */
public interface StandingsRepository extends JpaRepository<Standings, Long> {

    Standings findByTeam(String team);
}
