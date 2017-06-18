package com.devact.projects.championsleague.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Srdjan Simidzija
 */
@Getter
@Setter
@EqualsAndHashCode()
@Entity
@Table(name = "statistics")
public class Statistics extends LeagueStats{

    @Column(name = "cl_group", unique = true)
    protected String group;

    @Column
    @OneToMany
    private List<Standings> standings;
}
