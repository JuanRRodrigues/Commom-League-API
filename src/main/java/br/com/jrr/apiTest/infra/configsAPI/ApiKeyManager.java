package br.com.jrr.apiTest.infra.configsAPI;

import br.com.jrr.apiTest.domain.API.KeyRiotRegistrationAPI;

public class ApiKeyManager {


    private static String API_KEY = "RGAPI-4f60fcad-8a9e-4c17-8927-0b545cec02b1";

    public static String setApiKey(KeyRiotRegistrationAPI data) {
        API_KEY = data.apiKeyRiot();
        return API_KEY;
    }


    public static String getApiKey() {
        return API_KEY;
    }

}
