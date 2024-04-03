package br.com.jrr.apiTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamWebApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreamWebApplication.class, args);


	}
	@Override
	public void run(String... args) throws Exception {

		// json = getApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);

	//	FindApiData findApiData = new FindApiData();
	//	findApiData.findSerie();
		//findApiData.findMovie();


	}

}
