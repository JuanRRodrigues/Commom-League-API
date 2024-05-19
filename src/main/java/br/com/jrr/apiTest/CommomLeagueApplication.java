package br.com.jrr.apiTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CommomLeagueApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CommomLeagueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Aqui você pode adicionar a lógica que deseja executar ao iniciar a aplicação

		// Por exemplo, obter dados de uma API
		// JsonData jsonData = obterDadosDaApi("https://example.com/api/data");

		// Ou executar algum processamento inicial
		// processamentoInicial();

		// Ou qualquer outra coisa que seja necessária na inicialização da aplicação
	}

	// Exemplo de método para obter dados de uma API
	// private JsonData obterDadosDaApi(String url) {
	//     // Lógica para fazer a requisição HTTP e obter os dados da API
	// }

	// Exemplo de método para processamento inicial
	// private void processamentoInicial() {
	//     // Lógica de processamento inicial
	// }
}
