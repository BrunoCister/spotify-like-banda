package com.infnet.br.SpotifyLikeBanda.controller;

import com.infnet.br.SpotifyLikeBanda.MusicaController;
import com.infnet.br.SpotifyLikeBanda.application.MusicaService;
import com.infnet.br.SpotifyLikeBanda.domain.Musica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

@AutoConfigureMockMvc
@WebMvcTest(controllers = MusicaController.class)
public class MusicaControllerTest {

    @MockBean
    private MusicaService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveBuscarMusica() throws Exception {

        UUID musicaId = UUID.randomUUID();
        Musica musica = new Musica();
        musica.setId(musicaId);
        musica.setNome("Musica Teste");
        musica.setDuracao(120);

        given(this.service.getMusica(musicaId)).willReturn(Optional.of(musica));

        mvc.perform(get("/musica/" + musicaId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(musica.getId().toString()))
                        .andExpect(jsonPath("$.nome").value(musica.getNome()))
                        .andExpect(jsonPath("$.duracao").value(musica.getDuracao()));
    }

    @Test
    public void deveBuscarMusicaPorId() throws Exception {

        UUID musicaId = UUID.randomUUID();

        given(this.service.getMusica(musicaId)).willReturn(Optional.empty());

        mvc.perform(get("/musica/" + musicaId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }
}