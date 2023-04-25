package com.kevindotklein.springbootessentials.service;

import com.kevindotklein.springbootessentials.domain.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    //private final AnimeRepository animeRepository;

    private List<Anime> animes = List.of(new Anime(1L,"boku no hero"), new Anime(2L,"berserk"));

    public List<Anime> findAll(){
        return animes;
    }

    public Anime findById(Long id){
        return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "anime not found"));
    }
}
