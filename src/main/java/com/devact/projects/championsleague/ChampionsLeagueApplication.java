package com.devact.projects.championsleague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Srdjan Simidzija
 */

@EnableAutoConfiguration
@SpringBootApplication
public class ChampionsLeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChampionsLeagueApplication.class, args);
	}
}
