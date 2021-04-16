package br.com.texoit.texoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.texoit.texoit.service.LoadDataService;
import br.com.texoit.texoit.service.ReadFileService;
import br.com.texoit.texoit.utils.TexoitException;

@SpringBootApplication
public class TexoitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TexoitApplication.class, args);
		
		try {
			LoadDataService loadDataService = new LoadDataService();
			loadDataService.producers(new ReadFileService().read(args[0]));
		}catch (TexoitException texoitException) {
			texoitException.printStackTrace();
		}
	}

}
