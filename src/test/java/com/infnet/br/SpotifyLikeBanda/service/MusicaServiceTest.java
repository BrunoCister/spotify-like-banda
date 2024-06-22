package com.infnet.br.SpotifyLikeBanda.service;

import com.infnet.br.SpotifyLikeBanda.application.MusicaService;
import com.infnet.br.SpotifyLikeBanda.domain.Musica;
import com.infnet.br.SpotifyLikeBanda.repository.MusicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class MusicaServiceTest {

    @MockBean
    private MusicaRepository musicaRepository;

    @Autowired
    private MusicaService musicaService;

    @Test
    public void deveBuscarMusicaPorId() {

        UUID musicaId = UUID.randomUUID();
        Musica musica = new Musica();
        musica.setId(musicaId);
        musica.setNome("Musica Teste");
        musica.setDuracao(120);

        Optional<Musica> optionalMusica = Optional.of(musica);
        given(this.musicaRepository.findById(musicaId)).willReturn(optionalMusica);

        Optional<Musica> expected = this.musicaService.getMusica(musicaId);
        Assertions.assertTrue(expected.isPresent());
        Assertions.assertSame(expected, optionalMusica);
    }
}
