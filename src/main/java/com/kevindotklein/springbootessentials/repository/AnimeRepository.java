package com.kevindotklein.springbootessentials.repository;

import com.kevindotklein.springbootessentials.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> findAll();
}
