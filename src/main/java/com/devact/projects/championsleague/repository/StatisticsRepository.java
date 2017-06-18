package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Srdjan Simidzija
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Statistics findStatisticsByGroup(String group);
}

