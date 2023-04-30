package com.kevindotklein.springbootessentials.service;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.exception.BadRequestException;
import com.kevindotklein.springbootessentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> findAll(Pageable pageable){
        return this.animeRepository.findAll(pageable);
    }

    public Anime findById(Long id){
        return this.animeRepository.findById(id).orElseThrow(() -> new BadRequestException("anime not found"));
    }

    @Transactional
    public Anime save(Anime anime){
        return this.animeRepository.save(anime);
    }

    public void deleteById(Long id) {
        this.animeRepository.deleteById(findById(id).getId());
    }

    public void update(Anime anime) {
        this.animeRepository.save(anime);
    }

    public List<Anime> findByName(String name) {
        return this.animeRepository.findByName(name);
    }
}
