package com.kevindotklein.springbootessentials.repository;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.util.AnimeCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("tests for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("save anime when successful")
    void savePersistAnimeWhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("update anime when successful")
    void updatePersistAnimeWhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);

        savedAnime.setName("overlord");
        Anime updatedAnime = this.animeRepository.save(savedAnime);

        Assertions.assertThat(updatedAnime).isNotNull();
        Assertions.assertThat(updatedAnime.getId()).isNotNull();
        Assertions.assertThat(updatedAnime.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("delete anime when successful")
    void deletePersistAnimeWhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);

        this.animeRepository.delete(anime);

        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());
        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("find by name returns list of anime when successful")
    void findByNameReturnsListOfAnimeWhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);

        String name = savedAnime.getName();
        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes)
                .isNotEmpty()
                .contains(savedAnime);

    }

    @Test
    @DisplayName("find by name returns empty list anime is not found")
    void findByNameReturnsEmptyListWhenAnimeIsNotFound(){

        List<Anime> animes = this.animeRepository.findByName("xyz");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("save throws ConstraintViolationException when name is empty")
    void saveThrowsConstraintViolationExceptionWhenNameIsEmpty(){
        Anime anime = new Anime();
//        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime));

    }

}