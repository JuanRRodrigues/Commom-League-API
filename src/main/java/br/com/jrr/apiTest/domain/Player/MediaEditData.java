package br.com.jrr.apiTest.domain.Player;

import java.util.UUID;

public record MediaEditData(UUID id,
                            String title,
                            String releaseYear,
                            String boxOffice,
                            String type,
                            String poster,
                            String description,
                            String rated,
                            String releaseDate,
                            String runtime,
                            String genre,
                            String director,
                            String actores,
                            String language,
                            String country,
                            String awards,
                            String production) {

}
