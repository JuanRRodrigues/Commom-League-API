package br.com.jrr.apiTest.infra.configsAPI;

import br.com.jrr.apiTest.domain.API.KeyRiotRegistrationAPI;

public class ApiKeyManager {


    private static String API_KEY = "RGAPI-ab536c18-940c-4ee8-94cc-b20cf9f85175";

    public static String setApiKey(KeyRiotRegistrationAPI data) {
        API_KEY = data.apiKeyRiot();
        return API_KEY;
    }


    public static String getApiKey() {
        return API_KEY;
    }

}
