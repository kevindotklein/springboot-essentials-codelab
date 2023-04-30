package com.kevindotklein.springbootessentials.integration;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.dto.anime.AnimePostRequestDTO;
import com.kevindotklein.springbootessentials.mapper.AnimePostRequestDTOMapper;
import com.kevindotklein.springbootessentials.repository.AnimeRepository;
import com.kevindotklein.springbootessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AnimeControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("get all return list of animes page when successful")
    void getAllReturnsListOfAnimesPageWhenSuccessful(){

        Anime anime = AnimeCreator.createValidAnime();

        this.animeRepository.save(anime);

        List<Anime> animePage = restTemplate.exchange(
                "/animes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                }
        ).getBody();


        Assertions.assertThat(animePage)
                .isNotEmpty()
                .isNotNull();
//                .hasSize(1);

        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(anime.getName());
    }


    @Test
    @DisplayName("find by id returns anime when successful")
    void findByIdReturnsAnimeWhenSuccessful(){

        Anime savedAnime = this.animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        Long expectedId = savedAnime.getId();

        Anime expectedAnime = restTemplate.getForObject(
                "/animes/{id}",
                Anime.class,
                expectedId
        );

        Assertions.assertThat(expectedAnime)
                .isNotNull();
        Assertions.assertThat(expectedAnime.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("find by name returns list of anime when successful")
    void findByNameReturnsListOfAnimeWhenSuccessful(){

        Anime savedAnime = this.animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        String expectedName = savedAnime.getName();

        String url = String.format("/animes?name=%s", expectedName);

        List<Anime> animes = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                }
        ).getBody();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .isNotNull();
//                .hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("find by name returns an empty list of anime when anime is not found")
    void findByNameReturnsEmptyListOfAnimeWhenAnimeIsNotFound(){

        List<Anime> animes = restTemplate.exchange(
                "/animes?name=xyz",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                }
        ).getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();


    }

    @Test
    @DisplayName("save returns anime when successful")
    void saveReturnsAnimeWhenSuccessful(){

        AnimePostRequestDTO animePostRequestDTO = AnimePostRequestDTOMapper.INSTANCE.toAnimePostRequestDTO(AnimeCreator.createAnimeToBeSaved());

        ResponseEntity<Anime> anime = restTemplate.postForEntity(
                "/animes",
                animePostRequestDTO,
                Anime.class
        );

        Assertions.assertThat(anime)
                .isNotNull();

        Assertions.assertThat(anime.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(anime.getBody())
                .isNotNull();

        Assertions.assertThat(anime.getBody().getId())
                .isNotNull();

    }

    @Test
    @DisplayName("update returns nothing when successful")
    void updateReturnsNothingWhenSuccessful(){

        Anime savedAnime = this.animeRepository.save(AnimeCreator.createAnimeToBeSaved());
        savedAnime.setName("new name");

        ResponseEntity<Void> animeResponseEntity = restTemplate.exchange(
                "/animes/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(savedAnime),
                Void.class,
                savedAnime.getId()
        );

        Assertions.assertThat(animeResponseEntity)
                .isNotNull();

        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);


    }

    @Test
    @DisplayName("delete returns nothing when successful")
    void deleteReturnsNothingWhenSuccessful(){

        Anime savedAnime = this.animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        ResponseEntity<Void> animeResponseEntity = restTemplate.exchange(
                "/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedAnime.getId()
        );

        Assertions.assertThat(animeResponseEntity)
                .isNotNull();

        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
