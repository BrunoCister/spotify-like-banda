package com.infnet.br.SpotifyLikeBanda.repository;

import com.infnet.br.SpotifyLikeBanda.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
