package br.com.unisales.meupet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.unisales.meupet.repository.ProprietarioRepository;
import br.com.unisales.meupet.table.Proprietario;

@SpringBootTest
public class ProprietarioServiceTest {

    @InjectMocks
    private ProprietarioService proprietarioService;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    public ProprietarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        // Criando objetos fictícios
        Proprietario p1 = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);
        Proprietario p2 = new Proprietario(2, "Maria", "F", "987.654.321-01", "maria@email.com", "(11) 98765-4321", (byte) 1);

        // Configurando o mock para o método findAll
        when(proprietarioRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // Executando o método e verificando os resultados
        assertEquals(2, proprietarioService.listarTodos().size());
        assertEquals("João", proprietarioService.listarTodos().get(0).getNome());
        assertEquals("Maria", proprietarioService.listarTodos().get(1).getNome());
    }

    @Test
    public void testBuscarPorId() {
        // Criando um objeto fictício
        Proprietario p1 = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);

        // Configurando o mock para o método findById
        when(proprietarioRepository.findById(1)).thenReturn(Optional.of(p1));

        // Executando o método e verificando os resultados
        Optional<Proprietario> result = proprietarioService.buscarPorId(1);

        assertEquals(true, result.isPresent());
        assertEquals("João", result.get().getNome());
        assertEquals("M", result.get().getSexo());
        assertEquals("123.456.789-01", result.get().getCpf());
        assertEquals("joao@email.com", result.get().getEmail());
        assertEquals("(11) 91234-5678", result.get().getCelular());
    }

    @Test
    public void testSalvar() {
        // Criando um objeto fictício
        Proprietario p1 = new Proprietario(null, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);

        // Configurando o mock para o método save
        when(proprietarioRepository.save(p1)).thenReturn(new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1));

        // Executando o método e verificando os resultados
        Proprietario saved = proprietarioService.salvar(p1);

        assertEquals(1, saved.getId());
        assertEquals("João", saved.getNome());
        assertEquals("M", saved.getSexo());
        assertEquals("123.456.789-01", saved.getCpf());
        assertEquals("joao@email.com", saved.getEmail());
        assertEquals("(11) 91234-5678", saved.getCelular());
    }

    @Test
    public void testAtivar() {
        // Criando um objeto fictício inativo
        Proprietario p1 = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 0);

        // Configurando o mock para o método findById
        when(proprietarioRepository.findById(1)).thenReturn(Optional.of(p1));

        // Executando o método de ativação
        proprietarioService.ativar(1);

        // Verificando se o campo ativo foi alterado para 1
        assertEquals((byte) 1, p1.getAtivo());
        verify(proprietarioRepository, times(1)).save(p1);
    }

    @Test
    public void testInativar() {
        // Criando um objeto fictício ativo
        Proprietario p1 = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);

        // Configurando o mock para o método findById
        when(proprietarioRepository.findById(1)).thenReturn(Optional.of(p1));

        // Executando o método de inativação
        proprietarioService.inativar(1);

        // Verificando se o campo ativo foi alterado para 0
        assertEquals((byte) 0, p1.getAtivo());
        verify(proprietarioRepository, times(1)).save(p1);
    }
}
