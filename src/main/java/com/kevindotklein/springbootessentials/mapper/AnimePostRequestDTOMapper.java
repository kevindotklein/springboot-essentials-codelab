package com.kevindotklein.springbootessentials.mapper;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.dto.anime.AnimePostRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimePostRequestDTOMapper {

    public static final AnimePostRequestDTOMapper INSTANCE = Mappers.getMapper(AnimePostRequestDTOMapper.class);

    public abstract AnimePostRequestDTO toAnimePostRequestDTO(Anime anime);

}
