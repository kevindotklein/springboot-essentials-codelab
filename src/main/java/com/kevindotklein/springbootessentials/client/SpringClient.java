package com.kevindotklein.springbootessentials.client;

import com.kevindotklein.springbootessentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 4);
        //log.info(entity);

        //Anime obj = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 4);
        //log.info(obj);

        //Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes", Anime[].class);
        //log.info(Arrays.toString(animes));

        //List<Anime> animes = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.GET, null,
        //        new ParameterizedTypeReference<List<Anime>>() {}).getBody();
        //log.info(animes);

        //Anime mob = Anime.builder().name("mob psycho 100").build();
        //Anime mobSaved = new RestTemplate().postForObject("http://localhost:8080/animes", mob, Anime.class);
        //log.info(mobSaved);

//        Anime chainsawMan = Anime.builder().name("chainsaw man").build();
//        ResponseEntity<Anime> chainsawManSaved = new RestTemplate().exchange(
//                "http://localhost:8080/animes",
//                HttpMethod.POST,
//                new HttpEntity<>(chainsawMan),
//                Anime.class);
//        log.info(chainsawManSaved.getBody());
//
//        Anime animeSaved = chainsawManSaved.getBody();
//        animeSaved.setName("chainsaw man.");
//
//        ResponseEntity<Void> chainsawManUpdated = new RestTemplate().exchange(
//                "http://localhost:8080/animes/19",
//                HttpMethod.PUT,
//                new HttpEntity<>(animeSaved),
//                Void.class);
//
//        log.info(chainsawManUpdated);
//
//        ResponseEntity<Void> chainsawManDeleted = new RestTemplate().exchange(
//                "http://localhost:8080/animes/{id}",
//                HttpMethod.DELETE,
//                null,
//                Void.class,
//                19);
//
//        log.info(chainsawManDeleted);
    }
}
