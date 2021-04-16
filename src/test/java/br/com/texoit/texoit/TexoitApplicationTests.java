package br.com.texoit.texoit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.texoit.texoit.controllers.ChampionProduceController;

@SpringBootTest
class TexoitApplicationTests {
	
	@Autowired
	private ChampionProduceController championProduceController;

	@Test
	void contextLoads() throws Exception {
		assertThat(championProduceController).isNotNull();
	}

}
