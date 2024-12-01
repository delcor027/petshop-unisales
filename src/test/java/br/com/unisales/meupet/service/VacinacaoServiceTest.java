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

import br.com.unisales.meupet.repository.VacinacaoRepository;
import br.com.unisales.meupet.table.Pet;
import br.com.unisales.meupet.table.Vacinacao;

@SpringBootTest
public class VacinacaoServiceTest {

    @InjectMocks
    private VacinacaoService vacinacaoService;

    @Mock
    private VacinacaoRepository vacinacaoRepository;

    public VacinacaoServiceTest() {
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

        // Criando objetos fictícios para Vacinacao
        Vacinacao v1 = new Vacinacao(1, pet1, "Vacina A", "Descrição da Vacina A", new Date());
        Vacinacao v2 = new Vacinacao(2, pet2, "Vacina B", "Descrição da Vacina B", new Date());

        // Configurando o mock para o método findAll
        when(vacinacaoRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        // Executando o método e verificando os resultados
        assertEquals(2, vacinacaoService.listarTodos().size());
        assertEquals("Vacina A", vacinacaoService.listarTodos().get(0).getNome());
        assertEquals("Vacina B", vacinacaoService.listarTodos().get(1).getNome());
    }

    @Test
    public void testBuscarPorId() {
        // Criando objetos fictícios para Pet
        Pet pet = new Pet();
        pet.setId(1);
        pet.setNome("Max");

        // Criando objetos fictícios para Vacinacao
        Vacinacao v1 = new Vacinacao(1, pet, "Vacina A", "Descrição da Vacina A", new Date());

        // Configurando o mock para o método findById
        when(vacinacaoRepository.findById(1)).thenReturn(Optional.of(v1));

        // Executando o método e verificando os resultados
        Optional<Vacinacao> result = vacinacaoService.buscarPorId(1);

        assertEquals(true, result.isPresent());
        assertEquals("Vacina A", result.get().getNome());
        assertEquals("Descrição da Vacina A", result.get().getDescricao());
    }

    @Test
    public void testSalvar() {
        // Criando objetos fictícios para Pet
        Pet pet = new Pet();
        pet.setId(1);
        pet.setNome("Max");

        // Criando objetos fictícios para Vacinacao
        Vacinacao v1 = new Vacinacao(null, pet, "Vacina A", "Descrição da Vacina A", new Date());

        // Configurando o mock para o método save
        when(vacinacaoRepository.save(v1)).thenReturn(new Vacinacao(1, pet, "Vacina A", "Descrição da Vacina A", new Date()));

        // Executando o método e verificando os resultados
        Vacinacao saved = vacinacaoService.salvar(v1);

        assertEquals(1, saved.getId());
        assertEquals("Vacina A", saved.getNome());
        assertEquals("Descrição da Vacina A", saved.getDescricao());
    }

    @Test
    public void testDeletar() {
        // Simulando a deleção sem exceções
        vacinacaoService.deletar(1);
    }
}
