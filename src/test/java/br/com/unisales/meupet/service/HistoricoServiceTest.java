package br.com.unisales.meupet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.unisales.meupet.repository.HistoricoRepository;
import br.com.unisales.meupet.table.Historico;
import br.com.unisales.meupet.table.Pet;

@SpringBootTest
public class HistoricoServiceTest {

    @InjectMocks
    private HistoricoService historicoService;

    @Mock
    private HistoricoRepository historicoRepository;

    public HistoricoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        // Criando objetos fictícios para Pet
        Pet pet1 = new Pet();
        pet1.setId(1);
        pet1.setNome("Max");

        Pet pet2 = new Pet();
        pet2.setId(2);
        pet2.setNome("Bella");

        // Criando objetos fictícios para Historico
        Historico h1 = new Historico(1, pet1, 10.0, 40.0, new Date());
        Historico h2 = new Historico(2, pet2, 8.5, 35.0, new Date());

        // Configurando o mock para o método findAll
        when(historicoRepository.findAll()).thenReturn(Arrays.asList(h1, h2));

        // Executando o método e verificando os resultados
        assertEquals(2, historicoService.listarTodos().size());
        assertEquals("Max", historicoService.listarTodos().get(0).getPet().getNome());
        assertEquals("Bella", historicoService.listarTodos().get(1).getPet().getNome());
    }

    @Test
    public void testBuscarPorId() {
        // Criando objeto fictício para Pet
        Pet pet = new Pet();
        pet.setId(1);
        pet.setNome("Max");

        // Criando objeto fictício para Historico
        Historico h1 = new Historico(1, pet, 10.0, 40.0, new Date());

        // Configurando o mock para o método findById
        when(historicoRepository.findById(1)).thenReturn(Optional.of(h1));

        // Executando o método e verificando os resultados
        Optional<Historico> result = historicoService.buscarPorId(1);

        assertEquals(true, result.isPresent());
        assertEquals("Max", result.get().getPet().getNome());
        assertEquals(10.0, result.get().getPeso());
        assertEquals(40.0, result.get().getAltura());
    }

    @Test
    public void testSalvar() {
        // Criando objeto fictício para Pet
        Pet pet = new Pet();
        pet.setId(1);
        pet.setNome("Max");

        // Criando objeto fictício para Historico
        Historico h1 = new Historico(null, pet, 10.0, 40.0, new Date());

        // Configurando o mock para o método save
        when(historicoRepository.save(h1)).thenReturn(new Historico(1, pet, 10.0, 40.0, new Date()));

        // Executando o método e verificando os resultados
        Historico saved = historicoService.salvar(h1);

        assertEquals(1, saved.getId());
        assertEquals("Max", saved.getPet().getNome());
        assertEquals(10.0, saved.getPeso());
        assertEquals(40.0, saved.getAltura());
    }

    @Test
    public void testDeletar() {
        // Simulando a deleção sem exceções
        historicoService.deletar(1);
    }
}
