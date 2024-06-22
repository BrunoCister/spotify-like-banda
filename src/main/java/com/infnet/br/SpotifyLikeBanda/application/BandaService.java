package com.infnet.br.SpotifyLikeBanda.application;

import com.infnet.br.SpotifyLikeBanda.domain.Banda;
import com.infnet.br.SpotifyLikeBanda.repository.BandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BandaService {

    @Autowired
    BandaRepository bandaRepository;

    public void create(Banda banda) {
        this.bandaRepository.save(banda);
    }

    public Optional<Banda> getBanda(UUID id) {
        return this.bandaRepository.findById(id);
    }

    public List<Banda> getAllBanda() {
        return this.bandaRepository.findAll();
    }
}
