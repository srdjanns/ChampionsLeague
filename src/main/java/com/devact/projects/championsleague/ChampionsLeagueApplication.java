package com.devact.projects.championsleague;

import com.devact.projects.championsleague.dto.StatisticsDto;
import com.devact.projects.championsleague.model.Statistics;
import com.devact.projects.championsleague.repository.StatisticsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Srdjan Simidzija
 */

@EnableAutoConfiguration
@SpringBootApplication
public class ChampionsLeagueApplication {

	@Autowired
	private StatisticsRepository statisticsRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChampionsLeagueApplication.class, args);
	}

	// Data is inserted in the DB only for demonstration purposes, to skip manual population
	@PostConstruct
	private void populateDb() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		List<StatisticsDto> statisticsDtos = mapper.readValue(getFile("static/populate_db.json"), new TypeReference<List<StatisticsDto>>(){});
		List<Statistics> statistics = new ArrayList<>();
		for (StatisticsDto statisticsDto : statisticsDtos) {
			statistics.add(new Statistics(statisticsDto));
		}
		statisticsRepository.save(statistics);
	}

	private String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();

	}
}
