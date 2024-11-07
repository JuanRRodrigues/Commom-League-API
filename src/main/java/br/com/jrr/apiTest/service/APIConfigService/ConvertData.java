package br.com.jrr.apiTest.service.APIConfigService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertData implements IConvetData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDate(String json, Class<T> classe){
        try{
            return mapper.readValue(json, classe);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }

    // Método para converter JSON em List<String>
    public List<String> getDateAsList(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para List<String>: " + e.getMessage(), e);
        }
    }
}
