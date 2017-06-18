package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Srdjan Simidzija
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findById(String id) throws NoSuchElementException;

}

