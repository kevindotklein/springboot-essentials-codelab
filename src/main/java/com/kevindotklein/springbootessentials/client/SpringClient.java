package com.kevindotklein.springbootessentials.client;

import com.kevindotklein.springbootessentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 4);
        log.info(entity);

        Anime obj = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 4);
        log.info(obj);
    }
}
