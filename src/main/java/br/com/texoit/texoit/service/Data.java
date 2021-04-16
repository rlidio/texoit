package br.com.texoit.texoit.service;

import java.util.ArrayList;
import java.util.List;

import br.com.texoit.texoit.model.ChampionProducer;

public class Data {
	
	private static final List<ChampionProducer> championProducers = new ArrayList<>();
	
	private Data() {
		
	}
	public static List<ChampionProducer> getChampionProcuders(){
		return championProducers;
	}
	
	public static void addData(ChampionProducer producer) {
		championProducers.add(producer);
	}

}
