package com.infnet.br.SpotifyLikeBanda;

import com.infnet.br.SpotifyLikeBanda.application.BandaService;
import com.infnet.br.SpotifyLikeBanda.domain.Banda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banda")
public class BandaController {

    @Autowired
    BandaService bandaService;

    @GetMapping
    public ResponseEntity<List<Banda>> getAll() {

        List<Banda> banda = this.bandaService.getAllBanda();
        return new ResponseEntity<>(banda, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Banda> get(@PathVariable("id") UUID id) {

        return this.bandaService.getBanda(id)
                .map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Banda> create(@RequestBody Banda banda) {

        this.bandaService.create(banda);
        return new ResponseEntity<>(banda, HttpStatus.CREATED);
    }
}
