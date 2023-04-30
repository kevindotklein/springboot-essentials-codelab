package com.kevindotklein.springbootessentials.mapper;

import com.kevindotklein.springbootessentials.domain.Anime;
import com.kevindotklein.springbootessentials.dto.anime.AnimePutRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimePutRequestDTOMapper {

    public static final AnimePutRequestDTOMapper INSTANCE = Mappers.getMapper(AnimePutRequestDTOMapper.class);

    public abstract AnimePutRequestDTO toAnimePutRequestDTO(Anime anime);

}
