package br.com.jrr.apiTest;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SerieControllerTest {
/*
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SerieService service;
    @Mock
    private MovieRepository movieRepository;
    @Test
    void ShouldReturn400CodeForSerieRequestWithErrors() throws Exception {
        //ARRANGE
        String json = "{23}";
        //ACT
       var response = mvc.perform(
            post("/api/v1/series/post")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    void ShouldReturn200CodeForSerieRequestWithErrors() throws Exception {
        //ARRANGE
        String json = """
                    {
                    "title" : "Shrek"
                    }
                """;
        //ACT
        var response = mvc.perform(
                post("/api/v1/series/post")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestToListSeriesById() throws Exception {
        //ARRANGE
        UUID id = UUID.fromString("0f2f5af2-8795-4517-b386-e1711478586c");
        //ACT
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/movies/{id}", id)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn400StatusCodeToRequestToListSeriesById() throws Exception {
        //ARRANGE
        UUID id = UUID.fromString("0f3f5af8-8795-4517-b386-e1711478586c");
        //ACT
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/series/{}", id)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturn200swStatusCodeToRequestToAllListMovies() throws Exception {

        //Act
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/series/list")
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestToAllListSeries() throws Exception {

        //Act
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/series/list")
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestAlterSeries() throws Exception {

        //Arrange
        String json = """
                {
                "id": "0343ecff-bbbc-4f88-9b33-f1cfbf0e94f7",
                   "title": "ABABLUE",
                   "releaseYear": "2008",
                   "type": "TV Series",
                   "poster": "https://m.media-amazon.com/images/M/MV5BMzkyYWY3NTEtYzBkNC00Zjc3LThmY2UtZWU3MTRkMTgwODU5XkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg",
                   "totalSeasons": 4,
                   "rated": "TV-MA",
                   "releaseDate": "2008-01-20",
                   "runtime": "49 min per episode",
                   "genre": "Crime, Drama, Thriller",
                   "description": "A high school chemistry teacher turned methamphetamine manufacturer partners with a former student.",
                   "language": "English",
                   "country": "United States",
                   "awards": "16 Primetime Emmy Awards"
                }
                """;

        UUID id = UUID.fromString("0343ecff-bbbc-4f88-9b33-f1cfbf0e94f7");

        //Act
        MockHttpServletResponse response = mvc.perform(
                put("/api/v1/series/edit/{id}", id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn400StatusCodeToRequestAlterSeries() throws Exception {

        //Arrange
        String json = """
                {
                "id": "0931e55b-e1de-4825-8393-79c6b13a7ec8",
                  "title": "Teste",
                  "releaseYear": "2008",
         
                }
                """;

        UUID id = UUID.fromString("0931e55b-e1de-4825-8393-79c6b13a7ec8");

        //Act
        MockHttpServletResponse response = mvc.perform(
                put("/api/v1/movies/edit/{id}", id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestDeleteSeries() throws Exception {

        //Arrange
        UUID id = UUID.fromString("207e3459-8b3e-49dd-837f-062c51e69b86");

        //Act
        MockHttpServletResponse response = mvc.perform(
                delete("/api/v1/series/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn400StatusCodeToRequestDeleteSeries() throws Exception {

        //Arrange
        String id = "teste";

        //Act
        MockHttpServletResponse response = mvc.perform(
                delete("/api/v1/series/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }
*/

}
