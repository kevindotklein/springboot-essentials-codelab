package com.kevindotklein.springbootessentials.controller;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.mapper.AnimePostRequestDTOMapper;
import com.kevindotklein.springbootessentials.mapper.AnimePutRequestDTOMapper;
import com.kevindotklein.springbootessentials.service.AnimeService;
import com.kevindotklein.springbootessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(this.animeServiceMock.findAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(this.animeServiceMock.findById(anyLong()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(this.animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(this.animeServiceMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(this.animeServiceMock).update(ArgumentMatchers.any(Anime.class));

        BDDMockito.doNothing().when(this.animeServiceMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("get all return list of animes page when successful")
    void getAllReturnsListOfAnimesPageWhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animePage = this.animeController.getAll(null).getBody();

        Assertions.assertThat(animePage)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("find by id returns anime when successful")
    void findByIdReturnsAnimeWhenSuccessful(){

        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime expectedAnime = this.animeController.getById(1L).getBody();

        Assertions.assertThat(expectedAnime)
                .isNotNull();
        Assertions.assertThat(expectedAnime.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("find by name returns list of anime when successful")
    void findByNameReturnsListOfAnimeWhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = this.animeController.getByName("anime").getBody();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("find by name returns an empty list of anime when anime is not found")
    void findByNameReturnsEmptyListOfAnimeWhenAnimeIsNotFound(){

        BDDMockito.when(this.animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());


        List<Anime> animes = this.animeController.getByName("anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save returns anime when successful")
    void saveReturnsAnimeWhenSuccessful(){

        Anime anime = AnimeCreator.createValidAnime();
        Anime expectedAnime = this.animeController.save(AnimePostRequestDTOMapper.INSTANCE.toAnimePostRequestDTO(anime)).getBody();

        Assertions.assertThat(expectedAnime)
                .isNotNull();
        Assertions.assertThat(expectedAnime.getId())
                .isNotNull()
                .isEqualTo(anime.getId());
        Assertions.assertThat(expectedAnime).isEqualTo(anime);
    }

    @Test
    @DisplayName("update returns nothing when successful")
    void updateReturnsNothingWhenSuccessful(){

        Anime anime = AnimeCreator.createAnimeToBeSaved();
        
        Assertions.assertThatCode(() -> this.animeController.update(1L, AnimePutRequestDTOMapper.INSTANCE.toAnimePutRequestDTO(anime)))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = this.animeController.update(1L, AnimePutRequestDTOMapper.INSTANCE.toAnimePutRequestDTO(anime));

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("delete returns nothing when successful")
    void deleteReturnsNothingWhenSuccessful(){

        Assertions.assertThatCode(() -> this.animeController.deleteById(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = this.animeController.deleteById(1L);

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}