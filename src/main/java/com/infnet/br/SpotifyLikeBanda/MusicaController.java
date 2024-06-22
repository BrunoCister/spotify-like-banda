package com.infnet.br.SpotifyLikeBanda;

import com.infnet.br.SpotifyLikeBanda.application.MusicaService;
import com.infnet.br.SpotifyLikeBanda.domain.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    @Autowired
    MusicaService musicaService;

    @GetMapping("{id}")
    public ResponseEntity<Musica> get(@PathVariable("id") UUID id) {

        //Random random = new Random();
        //int numero = random.nextInt(100);

        return this.musicaService.getMusica(id)
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        /*if (numero % 2 == 0) {
            return this.musicaService.getMusica(id)
                    .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);*/
    }
}
