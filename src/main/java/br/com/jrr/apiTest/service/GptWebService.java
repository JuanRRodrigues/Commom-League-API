package br.com.jrr.apiTest.service;

import org.springdoc.core.service.OpenAPIService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class GptWebService {
    public static String obterResposta(String pergunta){
        OpenAiService service = new OpenAiService("Chave");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(pergunta)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}



