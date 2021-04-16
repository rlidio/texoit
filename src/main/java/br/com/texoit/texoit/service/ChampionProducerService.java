package br.com.texoit.texoit.service;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.texoit.texoit.model.ChampionProducer;
import br.com.texoit.texoit.model.WinnerPeriod;

@Service
public class ChampionProducerService implements CampionProducerImplementation {

	@Override
	public WinnerPeriod findWinningPeriod(List<ChampionProducer> championProducers) {
		List<ChampionProducer> winners = findProducersWinner(championProducers);
		Set<String> producerWinnerName = findProducersWinnerName(winners);

		producerWinnerName.removeIf(String::isEmpty);
		winners.sort(Comparator.comparing(ChampionProducer::getYear));
		Map<String, Integer> winnerCount = countProducersWinner(winners, producerWinnerName);
		winnerCount.values().removeIf(value -> value.equals(1));

		Map<String, Integer> intervalWin = new HashMap<>();
		winnerCount.keySet().stream().forEach(produceName -> loadPeriodWinnerByProducer(produceName, winners, intervalWin));

		return createWinnerPeriod(intervalWin);

	}

	private List<ChampionProducer> findProducersWinner(List<ChampionProducer> championProducers) {
		return championProducers.stream().filter(producer -> Boolean.TRUE.equals(producer.getWinner())).collect(Collectors.toList());
	}

	private Set<String> findProducersWinnerName(List<ChampionProducer> winners) {
		Set<String> producerWinnerName = new LinkedHashSet<>();
		winners.stream().forEachOrdered(winner -> Arrays.stream(winner.getProducer().split(",| and ")).map(String::trim).forEach(producerWinnerName::add));
		return producerWinnerName;
	}

	private Map<String, Integer> countProducersWinner(List<ChampionProducer> winners, Set<String> producerWinnerName) {
		Map<String, Integer> winnerCount = new HashMap<>();
		producerWinnerName.stream().forEach(producer -> winners.stream().forEachOrdered(winner -> {
			if (winner.getProducer().contains(producer)) {
				Integer count = Optional.ofNullable(winnerCount.get(producer)).orElse(0);
				winnerCount.put(producer, ++count);
			}
		}));
		return winnerCount;
	}

	private void loadPeriodWinnerByProducer(String produceName, List<ChampionProducer> winners, Map<String, Integer> intervalWin) {
		List<ChampionProducer> produceWinners = winners.stream().filter(winnProduce -> winnProduce.getProducer().contains(produceName)).collect(Collectors.toList());

		Integer previousYearWinn = null;

		for (int count = 0; count < produceWinners.size(); count++) {
			ChampionProducer winnProduce = produceWinners.get(count);
			Integer intervalWinn = intervalWin.get(produceName);

			if (isFirstRegister(previousYearWinn, intervalWinn)) {
				intervalWin.put(produceName, null);
				previousYearWinn = winnProduce.getYear();
				continue;
			}

			intervalWin.put(produceName, winnProduce.getYear() - previousYearWinn);
			previousYearWinn = winnProduce.getYear();
		}

	}

	private boolean isFirstRegister(Integer year, Integer intervalWinn) {
		return !Optional.ofNullable(intervalWinn).isPresent() && !Optional.ofNullable(year).isPresent();
	}

	private WinnerPeriod createWinnerPeriod(Map<String, Integer> intervalWinn) {
		Optional<Entry<String, Integer>> min = intervalWinn.entrySet().stream().min(Map.Entry.comparingByValue());
		Optional<Entry<String, Integer>> maxEntry = intervalWinn.entrySet().stream().max(Map.Entry.comparingByValue());

		WinnerPeriod winner = new WinnerPeriod();
		winner.setWinningProducerlongestPeriod(maxEntry.orElse(new AbstractMap.SimpleEntry<>(null, null)).getKey());
		winner.setWinningProducerShorterPeriod(min.orElse(new AbstractMap.SimpleEntry<>(null, null)).getKey());
		return winner;
	}
}
