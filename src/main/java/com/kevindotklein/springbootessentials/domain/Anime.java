package com.kevindotklein.springbootessentials.domain;

import com.kevindotklein.springbootessentials.dto.anime.AnimePostRequestDTO;
import com.kevindotklein.springbootessentials.dto.anime.AnimePutRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Anime(AnimePostRequestDTO data){
        this.name = data.name();
    }
    public Anime(AnimePutRequestDTO data){
        this.name = data.name();
    }

    public void updateAllAttributes(String name){
        this.name = name;
    }
}
