package com.devact.projects.championsleague.service;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srdjan Simidzija
 */

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<MatchDto> findAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(match -> new MatchDto(match))
                .collect(Collectors.toList());
    }

}
