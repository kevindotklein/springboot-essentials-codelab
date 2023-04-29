package com.kevindotklein.springbootessentials.controller;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.dto.anime.AnimePostRequestDTO;
import com.kevindotklein.springbootessentials.dto.anime.AnimePutRequestDTO;
import com.kevindotklein.springbootessentials.mapper.AnimeMapper;
import com.kevindotklein.springbootessentials.service.AnimeService;
import com.kevindotklein.springbootessentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animes")
@Log4j2
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> getAll(){
        return ResponseEntity.ok(this.animeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.animeService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> getByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(this.animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestDTO data){
        Anime anime = AnimeMapper.INSTANCE.toAnime(data);
        return new ResponseEntity<>(this.animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AnimePutRequestDTO data){
        Anime anime = AnimeMapper.INSTANCE.toAnime(data);
        anime.setId(this.animeService.findById(id).getId());
        this.animeService.update(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
