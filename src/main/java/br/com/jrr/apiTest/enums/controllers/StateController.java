package br.com.jrr.apiTest.enums.controllers;

import br.com.jrr.apiTest.enums.DTO.StateDTO;
import br.com.jrr.apiTest.enums.adress.State;
import br.com.jrr.apiTest.enums.adress.Country;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @GetMapping("/{country}")
    public List<StateDTO> getStatesByCountry(@PathVariable Country country) {
        List<StateDTO> statesInCountry = new ArrayList<>();

        if (country == null) {
            return statesInCountry;
        }

        for (State state : State.values()) {
            if (state.getCountry() == country) {
                statesInCountry.add(new StateDTO(state.getName(), state.getCountry()));
            }
        }

        return statesInCountry;
    }

    @GetMapping("/")
    public List<StateDTO> getAllStates() {
        List<StateDTO> allStates = new ArrayList<>();

        for (State state : State.values()) {
            allStates.add(new StateDTO(state.getName(), state.getCountry()));
        }

        return allStates;
    }

    @PostMapping("/")
    public StateDTO createState(@RequestBody StateDTO stateDTO) {
        // Como estamos lidando com enums, não podemos criar um novo enum diretamente.
        // Vamos apenas simular a criação de um novo estado e retornar o DTO.
        // Não é possível alterar ou adicionar enums de forma dinâmica em tempo de execução.

        // Apenas simulamos um novo estado retornado
        return new StateDTO(stateDTO.getName(), stateDTO.getCountry());
    }

    @PutMapping("/{name}")
    public StateDTO updateState(@PathVariable String name, @RequestBody StateDTO stateDTO) {
        // Simulação de atualização de estado
        // Novamente, como enums são constantes, não podemos alterar isso diretamente.

        return new StateDTO(stateDTO.getName(), stateDTO.getCountry());
    }

    @DeleteMapping("/{name}")
    public String deleteState(@PathVariable String name) {
        // Simulação de remoção de um estado
        return "O estado " + name + " foi removido (simulação).";
    }
}
