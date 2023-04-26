package com.kevindotklein.springbootessentials.service;

import com.kevindotklein.springbootessentials.domain.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {

    //private final AnimeRepository animeRepository;

    private static List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L,"boku no hero"), new Anime(2L,"berserk")));
    }

    public List<Anime> findAll(){
        return animes;
    }

    public Anime findById(Long id){
        return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "anime not found"));
    }

    public Anime save(Anime anime){
        anime.setId(ThreadLocalRandom.current().nextLong(3, 1000000));
        animes.add(anime);
        return anime;
    }

    public void deleteById(Long id) {
        animes.remove(findById(id));
    }

    public void update(Anime anime) {
        deleteById(anime.getId());
        animes.add(anime);
    }
}
