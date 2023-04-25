package com.kevindotklein.springbootessentials.controller;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.service.AnimeService;
import com.kevindotklein.springbootessentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    public ResponseEntity<Anime> getAll(@PathVariable Long id){
        return ResponseEntity.ok(this.animeService.findById(id));
    }
}
