package br.com.jrr.apiTest.service;




import br.com.jrr.apiTest.domain.API.DataAccountPaymentAPI;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class MercadoPagoWebService {

    public String Payment(DataAccountPaymentAPI data) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"id\": \"Coins CommomLeague\",\n" +
                "      \"title\": \"Commom League\",\n" +
                "      \"description\": \"Commom description\",\n" +
                "      \"picture_url\": \"http://www.myapp.com/myimage.jpg\",\n" +
                "      \"category_id\": \"Entreniment\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"currency_id\": \"BRL\",\n" +
                "     \"unit_price\": " + Integer.parseInt(data.unit_price()) + "\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.mercadopago.com/checkout/preferences"))
                    .header("Authorization", "Bearer TEST-6807336650098955-050509-4238bc3cf798b172735e058172b6313d-1793899503")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int initPointIndex = responseBody.indexOf("init_point") + 13; // index do início do link
            int endIndex = responseBody.indexOf("\"", initPointIndex); // index do fim do link
            String mercadoPagoLink = responseBody.substring(initPointIndex, endIndex);

            return mercadoPagoLink;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
