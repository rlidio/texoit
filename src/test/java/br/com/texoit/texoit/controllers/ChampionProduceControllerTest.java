package br.com.texoit.texoit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.texoit.texoit.model.WinnerPeriod;
import br.com.texoit.texoit.service.ChampionProducerService;
import br.com.texoit.texoit.service.Data;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers=ChampionProduceController.class)
public class ChampionProduceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ChampionProducerService campionProducerService;

	@Test
	public void shouldSayHello() throws Exception {
		MvcResult result = mockMvc.perform(get("/")).andReturn();
		assertEquals("hello texoit",result.getResponse().getContentAsString());
	}
	
	@Test
	public void shouldReturnProducerWinnerShortPeriod() throws Exception {
		when(campionProducerService.findWinningPeriod(Data.getChampionProcuders())).thenReturn(createWiningPeriod("Joel Silver","Matthew Vaughn"));
		MvcResult result = mockMvc.perform(get("/winner-short-period")).andReturn();
		assertEquals("Joel Silver",result.getResponse().getContentAsString());
	}
	
	@Test
	public void shouldReturnProducerWinnerlongestPeriod() throws Exception {
		when(campionProducerService.findWinningPeriod(Data.getChampionProcuders())).thenReturn(createWiningPeriod("Joel Silver","Matthew Vaughn"));
		MvcResult result = mockMvc.perform(get("/winner-long-period")).andReturn();
		assertEquals("Matthew Vaughn",result.getResponse().getContentAsString());
	}
	
	private WinnerPeriod createWiningPeriod(String shortWinningProcuder,String longestWinnerProducer) {
		WinnerPeriod winningPeriod = new WinnerPeriod();
		winningPeriod.setWinningProducerlongestPeriod(longestWinnerProducer);
		winningPeriod.setWinningProducerShorterPeriod(shortWinningProcuder);
		return winningPeriod;
	}

}
