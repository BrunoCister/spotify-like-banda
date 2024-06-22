package com.infnet.br.SpotifyLikeBanda.application;

import com.infnet.br.SpotifyLikeBanda.domain.Musica;
import com.infnet.br.SpotifyLikeBanda.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MusicaService {

    @Autowired
    MusicaRepository musicaRepository;

    public Optional<Musica> getMusica(UUID id) {
        return this.musicaRepository.findById(id);
    }
}
