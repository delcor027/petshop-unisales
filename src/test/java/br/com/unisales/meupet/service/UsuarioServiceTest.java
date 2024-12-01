package br.com.unisales.meupet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.unisales.meupet.repository.UsuarioRepository;
import br.com.unisales.meupet.table.Usuario;

@SpringBootTest
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        // Criando objetos fictícios
        Usuario u1 = new Usuario(1, "João", "M", "joao@email.com", "senha123", "Admin", 1);
        Usuario u2 = new Usuario(2, "Maria", "F", "maria@email.com", "senha456", "User", 1);

        // Configurando o mock para o método findAll
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        // Executando o método e verificando os resultados
        assertEquals(2, usuarioService.listarTodos().size());
        assertEquals("João", usuarioService.listarTodos().get(0).getNome());
        assertEquals("Maria", usuarioService.listarTodos().get(1).getNome());
    }

    @Test
    public void testBuscarPorId() {
        // Criando objeto fictício
        Usuario u1 = new Usuario(1, "João", "M", "joao@email.com", "senha123", "Admin", 1);

        // Configurando o mock para o método findById
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(u1));

        // Executando o método e verificando os resultados
        Optional<Usuario> result = usuarioService.buscarPorId(1);

        assertEquals(true, result.isPresent());
        assertEquals("João", result.get().getNome());
        assertEquals("Admin", result.get().getGrupo());
        assertEquals(1, result.get().getAtivo());
    }

    @Test
    public void testSalvar() {
        // Criando objeto fictício
        Usuario u1 = new Usuario(null, "João", "M", "joao@email.com", "senha123", "Admin", 1);

        // Configurando o mock para o método save
        when(usuarioRepository.save(u1)).thenReturn(new Usuario(1, "João", "M", "joao@email.com", "senha123", "Admin", 1));

        // Executando o método e verificando os resultados
        Usuario saved = usuarioService.salvar(u1);

        assertEquals(1, saved.getId());
        assertEquals("João", saved.getNome());
        assertEquals("Admin", saved.getGrupo());
    }

    @Test
    public void testDeletar() {
        // Simulando a deleção sem exceções
        usuarioService.deletar(1);
    }
}
