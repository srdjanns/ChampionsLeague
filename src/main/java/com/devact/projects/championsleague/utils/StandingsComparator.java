package com.devact.projects.championsleague.utils;

import com.devact.projects.championsleague.model.Standings;

import java.util.Comparator;

/**
 * @author Srdjan Simidzija
 */
public class StandingsComparator implements Comparator<Standings> {

    @Override public int compare(final Standings s1, final Standings s2) {
        int res = Integer.valueOf(s2.getPoints()).compareTo(Integer.valueOf(s1.getPoints()));
        if (res != 0) return res;

        res = Integer.valueOf(s2.getGoals()).compareTo(Integer.valueOf(s1.getGoals()));
        if (res != 0) return res;

        return Integer.valueOf(s2.getGoalDifference()).compareTo(Integer.valueOf(s1.getGoalDifference()));
    }
}
