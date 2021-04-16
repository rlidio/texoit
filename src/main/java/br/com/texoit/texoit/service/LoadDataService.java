package br.com.texoit.texoit.service;

import java.util.List;

import br.com.texoit.texoit.model.ChampionProducer;
import br.com.texoit.texoit.utils.MappingMovieFile;

public class LoadDataService {

	private static final String SEPARATOR = ";";

	public void producers(List<String> lines) {
		lines.stream().forEach(line -> {
			if (line.split(SEPARATOR).length > 3) {
				Data.addData(createChampionProducer(line.split(SEPARATOR)));
			}
		});
	}

	private ChampionProducer createChampionProducer(String[] lineSplit) {
		ChampionProducer championProducer = new ChampionProducer();
		championProducer.setYear(Integer.valueOf(lineSplit[MappingMovieFile.YEAR.ordinal()]));
		championProducer.setTitle(lineSplit[MappingMovieFile.TITLE.ordinal()]);
		championProducer.setStudio(lineSplit[MappingMovieFile.STUDIO.ordinal()]);
		championProducer.setProducer(lineSplit[MappingMovieFile.PRODUCER.ordinal()]);

		if (isWinner(lineSplit)) {
			championProducer.setWinner(lineSplit[MappingMovieFile.WINNER.ordinal()].toLowerCase().trim().equals("yes"));
		}
		
		return championProducer;
	}

	private boolean isWinner(String[] lineSplit) {
		return lineSplit.length > 4;
	}

}
