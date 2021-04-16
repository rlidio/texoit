package br.com.texoit.texoit.service;

import java.util.List;

import br.com.texoit.texoit.model.ChampionProducer;
import br.com.texoit.texoit.model.WinnerPeriod;

public interface CampionProducerImplementation {

	WinnerPeriod findWinningPeriod(List<ChampionProducer> championProducer);
	
}