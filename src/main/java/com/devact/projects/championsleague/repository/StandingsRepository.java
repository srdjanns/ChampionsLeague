package com.devact.projects.championsleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devact.projects.championsleague.model.Standings;

/**
 * @author Srdjan Simidzija
 */
public interface StandingsRepository extends JpaRepository<Standings, Long> {

    Standings findByTeam(String team);
}
