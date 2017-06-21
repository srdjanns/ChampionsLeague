package com.devact.projects.championsleague.repository;

import com.devact.projects.championsleague.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Srdjan Simidzija
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Transactional
    void deleteStatisticsByGroup(String group);

    Optional<Statistics> findStatisticsByGroupIgnoreCase(String group);
}
