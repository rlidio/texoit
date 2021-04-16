package br.com.texoit.texoit.model;

import lombok.Data;

@Data
public class ChampionProducer {
	
	private Integer year;
	private String title;
	private String studio;
	private String producer;
	private Boolean winner;

}
