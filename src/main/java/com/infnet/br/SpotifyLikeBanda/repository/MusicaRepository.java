package com.infnet.br.SpotifyLikeBanda.repository;

import com.infnet.br.SpotifyLikeBanda.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
