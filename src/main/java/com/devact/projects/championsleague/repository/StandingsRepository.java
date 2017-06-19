package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;

/**
 * Created by Srdjan on 18/06/17.
 */
public interface StandingsRepository extends JpaRepository<Standings, Long> {

    Standings findByTeam(String team);
}
