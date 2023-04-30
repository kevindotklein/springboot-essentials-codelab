package com.kevindotklein.springbootessentials.service;


import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.exception.BadRequestException;
import com.kevindotklein.springbootessentials.repository.AnimeRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(this.animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(this.animeRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(this.animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(this.animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(this.animeRepositoryMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("find all return list of animes page when successful")
    void findAllReturnsListOfAnimesPageWhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = this.animeService.findAll(PageRequest.of(1,2)).getContent();

        Assertions.assertThat(animes)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("find by id returns anime when successful")
    void findByIdReturnsAnimeWhenSuccessful(){

        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime expectedAnime = this.animeService.findById(1L);

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
        List<Anime> animes = this.animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("find by name returns an empty list of anime when anime is not found")
    void findByNameReturnsEmptyListOfAnimeWhenAnimeIsNotFound(){

        BDDMockito.when(this.animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());


        List<Anime> animes = this.animeService.findByName("anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save returns anime when successful")
    void saveReturnsAnimeWhenSuccessful(){

        Anime anime = AnimeCreator.createValidAnime();
        Anime expectedAnime = this.animeService.save(anime);

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

        Assertions.assertThatCode(() -> this.animeService.update(anime))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("delete returns nothing when successful")
    void deleteReturnsNothingWhenSuccessful(){

        Assertions.assertThatCode(() -> this.animeService.deleteById(1L))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName("find by id throws BadRequestException when anime is not found")
    void findByIdThrowsBadRequestExceptionWhenAnimeIsNotFound(){

        BDDMockito.when(this.animeRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> this.animeService.findById(1L));
  
    }

}