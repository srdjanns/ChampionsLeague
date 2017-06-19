package com.devact.projects.championsleague.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devact.projects.championsleague.model.Match;

/**
 * @author Srdjan Simidzija
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findById(String id) throws NoSuchElementException;

}
