package com.devact.projects.championsleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devact.projects.championsleague.model.Statistics;

/**
 * @author Srdjan Simidzija
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Statistics findStatisticsByGroup(String group);
}
