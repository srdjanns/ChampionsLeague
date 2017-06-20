package com.devact.projects.championsleague.utils;

import com.devact.projects.championsleague.dto.MatchDto;
import com.devact.projects.championsleague.model.Match;
import com.devact.projects.championsleague.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srdjan Simidzija
 *
 * Util class for common functionalities for matches
 */
public class MatchUtils {

    public static final Logger logger = LoggerFactory.getLogger(MatchUtils.class);

    public static void insertMatches(final MatchRepository matchRepository, final List<MatchDto> matchesDto) {
        List<Match> matches = new ArrayList<>();
        matches = matchesDto.stream().map(match -> {
            try {
                return new Match(match);
            } catch (ParseException e) {
                logger.info("Error while parsing match object...");
            }
            return null;
        }).collect(Collectors.toList());
        matchRepository.save(matches);
    }

    public static List<MatchDto> convertMatchesIntoMatchesDto(List<Match> matches) {
        return matches.stream().map(match -> new MatchDto(match)).collect(Collectors.toList());
    }

    public static List<String> populateGroups(final List<MatchDto> matches) {
        return matches.stream().map(match -> match.getGroup()).distinct().collect(Collectors.toList());
    }

    public static String ifNotNull(String parameter) {
        if (parameter == null) {
            return "";
        }
        return parameter;
    }

    public static Date ifNotNullDateFrom(Date parameter) {
        if (parameter == null) {
            // return date a year ago, no matches are before that time
            return new Date(new Date().getTime() - (365 * 24 * 60 * 60 * 1000));
        }
        return parameter;
    }

    public static Date ifNotNullDateAfter(Date parameter) {
        if (parameter == null) {
            // return current date, no match can come from the future
            return new Date();
        }
        return parameter;
    }
}
