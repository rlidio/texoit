package br.com.texoit.texoit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.texoit.texoit.model.WinnerPeriod;
import br.com.texoit.texoit.service.ChampionProducerService;
import br.com.texoit.texoit.service.Data;

@RestController
@RequestMapping("/")
public class ChampionProduceController {
	
	@Autowired
	private ChampionProducerService campionProducerService;
	
	@GetMapping()
	public String hello() {
		return "hello texoit";
	}
	
	@GetMapping("winner-period")
	public ResponseEntity<WinnerPeriod> winnerPeriod() {
		return ResponseEntity.ok(campionProducerService.findWinningPeriod(Data.getChampionProcuders()));
	}
	
	@GetMapping("winner-short-period")
	public ResponseEntity<String> winnerShortPeriod() {
		WinnerPeriod winningPeriod = campionProducerService.findWinningPeriod(Data.getChampionProcuders());
		return ResponseEntity.ok(winningPeriod.getWinningProducerShorterPeriod());
	}
	
	@GetMapping("winner-long-period")
	public ResponseEntity<String> winnerLongPeriod() {
		WinnerPeriod winningPeriod = campionProducerService.findWinningPeriod(Data.getChampionProcuders());
		return ResponseEntity.ok(winningPeriod.getWinningProducerlongestPeriod());
	}
	
}
