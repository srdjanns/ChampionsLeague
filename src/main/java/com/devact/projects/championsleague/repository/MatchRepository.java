package com.devact.projects.championsleague.repository;

import java.util.List;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.devact.projects.championsleague.model.Match;

/**
 * @author Srdjan Simidzija
 */
public interface MatchRepository extends JpaRepository<Match, Long>, QueryDslPredicateExecutor<Match> {

    List<Match> findAll(Predicate predicate);
}
