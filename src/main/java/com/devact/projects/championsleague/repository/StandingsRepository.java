package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Srdjan Simidzija.
 */
public interface StandingsRepository extends JpaRepository<Standings, Long> {

    Standings findFirstByTeamIgnoreCase(String team);
}
