package com.infnet.br.SpotifyLikeBanda.service;

import com.infnet.br.SpotifyLikeBanda.application.BandaService;
import com.infnet.br.SpotifyLikeBanda.domain.Banda;
import com.infnet.br.SpotifyLikeBanda.repository.BandaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BandaServiceTest {

    @MockBean
    private BandaRepository bandaRepository;

    @Autowired
    private BandaService bandaService;

    @Test
    public void deveBuscarTodasAsBandas() {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Teste");
        banda.setDescricao("Descricao Teste");

        ArrayList<Banda> bandas = new ArrayList<>();
        bandas.add(banda);

        given(this.bandaRepository.findAll()).willReturn(bandas);

        List<Banda> expected = this.bandaService.getAllBanda();
        Assertions.assertArrayEquals(bandas.toArray(), expected.toArray());
    }

    @Test
    public void deveBuscarBandaPorId() {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Teste");
        banda.setDescricao("Descricao Teste");

        Optional<Banda> optionalBanda = Optional.of(banda);
        given(this.bandaRepository.findById(idBanda)).willReturn(optionalBanda);

        Optional<Banda> expected = this.bandaService.getBanda(idBanda);
        Assertions.assertTrue(expected.isPresent());
        Assertions.assertSame(expected, optionalBanda);
    }

    @Test
    public void deveCriarBanda() {

        UUID idBanda =  UUID.randomUUID();
        Banda banda = new Banda();
        banda.setId(idBanda);
        banda.setNome("Banda Teste");
        banda.setDescricao("Descricao Teste");

        this.bandaService.create(banda);

        verify(this.bandaRepository, times(1)).save(banda);
    }
}
