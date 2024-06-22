package com.infnet.br.SpotifyLikeBanda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.br.SpotifyLikeBanda.BandaController;
import com.infnet.br.SpotifyLikeBanda.application.BandaService;
import com.infnet.br.SpotifyLikeBanda.domain.Banda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = BandaController.class)
public class BandaControllerTest {
    @MockBean
    private BandaService bandaService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveBuscarTodasAsBandas() throws Exception{

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        ArrayList<Banda> bandas = new ArrayList<>();
        bandas.add(banda);

        given(bandaService.getAllBanda()).willReturn(bandas);

        mvc.perform(get("/banda")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].nome").value(banda.getNome()))
                        .andExpect(jsonPath("$[0].id").value(banda.getId().toString()))
                        .andExpect(jsonPath("$[0].descricao").value(banda.getDescricao()));
    }

    @Test
    public void deveBuscarBandaPorId() throws Exception {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Dummy");
        banda.setDescricao("Descricao Dummy");

        Optional<Banda> optionalBanda = Optional.of(banda);
        given(this.bandaService.getBanda(idBanda)).willReturn(optionalBanda);

        mvc.perform(get("/banda/" + idBanda)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.nome").value(banda.getNome()))
                        .andExpect(jsonPath("$.id").value(banda.getId().toString()))
                        .andExpect(jsonPath("$.descricao").value(banda.getDescricao()));
    }

    @Test
    public void deveBuscarBandaPorIdNotFound() throws Exception {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Teste");
        banda.setDescricao("Descricao Teste");

        given(this.bandaService.getBanda(idBanda)).willReturn(Optional.empty());

        mvc.perform(get("/banda/" + idBanda)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void deveCriarBanda() throws Exception {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Teste");
        banda.setDescricao("Descricao Teste");

        mvc.perform(post("/banda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(banda)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.nome").value(banda.getNome()))
                        .andExpect(jsonPath("$.id").value(banda.getId().toString()))
                        .andExpect(jsonPath("$.descricao").value(banda.getDescricao()));
    }



}
