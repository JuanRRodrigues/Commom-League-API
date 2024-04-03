package br.com.jrr.apiTest;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
/*
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService service;
    @Mock
    private MovieRepository movieRepository;
    @Test
    void ShouldReturn400CodeForMovienRequestWithErrors() throws Exception {
        //ARRANGE
        String json = "{23}";
        //ACT
       var response = mvc.perform(
            post("/api/v1/movies/post")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    void ShouldReturn200CodeForMovieRequestWithErrors() throws Exception {
        //ARRANGE
        String json = """
                    {
                    "title" : "Shrek"
                    }
                """;
        //ACT
        var response = mvc.perform(
                post("/api/v1/movies/post")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeParaRequestToListMoviesById() throws Exception {
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
    void shouldReturn400StatusCodeToRequestToListMoviesById() throws Exception {
        //ARRANGE
        UUID id = UUID.fromString("0f3f5af8-8795-4517-b386-e1711478586c");
        //ACT
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/movies/{}", id)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturn200swStatusCodeToRequestToAllListMovies() throws Exception {

        //Act
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/movies/list")
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestToAllListMovies() throws Exception {

        //Act
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/movies/list")
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn200StatusCodeToRequestAlterMovies() throws Exception {

        //Arrange
        String json = """
                {
                "id": "0f2f5af2-8795-4517-b386-e1711478586c",
                  "title": "Teste",
                  "releaseYear": "2008",
                  "type": "TV Series",
                  "poster": "https://m.media-amazon.com/images/M/MV5BMzkyYWY3NTEtYzBkNC00Zjc3LThmY2UtZWU3MTRkMTgwODU5XkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg",
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

        UUID id = UUID.fromString("0f2f5af2-8795-4517-b386-e1711478586c");

        //Act
        MockHttpServletResponse response = mvc.perform(
                put("/api/v1/movies/edit/{id}", id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn400StatusCodeToRequestAlterMovies() throws Exception {

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
    void shouldReturn200StatusCodeToRequestDeleteMovies() throws Exception {

        //Arrange
        UUID id = UUID.fromString("0931e55b-e1de-4825-8393-79c6b13a7ec8");

        //Act
        MockHttpServletResponse response = mvc.perform(
                delete("/api/v1/movies/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldReturn400StatusCodeToRequestDeleteMovies() throws Exception {

        //Arrange
        String id = "teste";

        //Act
        MockHttpServletResponse response = mvc.perform(
                delete("/api/v1/movies/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(400, response.getStatus());
    }
*/

}
