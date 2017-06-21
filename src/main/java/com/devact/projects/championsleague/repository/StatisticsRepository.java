package com.devact.projects.championsleague.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devact.projects.championsleague.model.Statistics;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Srdjan Simidzija
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Transactional
    void deleteStatisticsByGroup(String group);

    Optional<Statistics> findStatisticsByGroup(String group);
}
