package com.kevindotklein.springbootessentials.util;

import com.kevindotklein.springbootessentials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder().name("hajime no ippo").build();
    }

    public static Anime createValidAnime(){
        return Anime.builder().name("hajime no ippo").id(99L).build();
    }

    public static Anime createAnimeValidUpdatedAnime(){
        return Anime.builder().name("hajime no ippo...").id(99L).build();
    }
}
