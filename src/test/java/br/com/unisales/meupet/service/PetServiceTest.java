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

import br.com.unisales.meupet.repository.PetRepository;
import br.com.unisales.meupet.table.Pet;
import br.com.unisales.meupet.table.Proprietario;

@SpringBootTest
public class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        // Criando objetos fictícios para Proprietario
        Proprietario proprietario1 = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);
        Proprietario proprietario2 = new Proprietario(2, "Maria", "F", "987.654.321-01", "maria@email.com", "(11) 98765-4321", (byte) 1);

        // Criando objetos fictícios para Pet
        Pet pet1 = new Pet(1, proprietario1, "Max", "2020-01-01", "M", "Cachorro", "Labrador");
        Pet pet2 = new Pet(2, proprietario2, "Bella", "2019-06-15", "F", "Gato", "Siamês");

        // Configurando o mock para o método findAll
        when(petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        // Executando o método e verificando os resultados
        assertEquals(2, petService.listarTodos().size());
        assertEquals("Max", petService.listarTodos().get(0).getNome());
        assertEquals("Bella", petService.listarTodos().get(1).getNome());
    }

    @Test
    public void testBuscarPorId() {
        // Criando objetos fictícios para Proprietario
        Proprietario proprietario = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);

        // Criando objetos fictícios para Pet
        Pet pet = new Pet(1, proprietario, "Max", "2020-01-01", "M", "Cachorro", "Labrador");

        // Configurando o mock para o método findById
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        // Executando o método e verificando os resultados
        Optional<Pet> result = petService.buscarPorId(1);

        assertEquals(true, result.isPresent());
        assertEquals("Max", result.get().getNome());
        assertEquals("Cachorro", result.get().getEspecie());
        assertEquals("Labrador", result.get().getRaca());
    }

    @Test
    public void testSalvar() {
        // Criando objetos fictícios para Proprietario
        Proprietario proprietario = new Proprietario(1, "João", "M", "123.456.789-01", "joao@email.com", "(11) 91234-5678", (byte) 1);

        // Criando objetos fictícios para Pet
        Pet pet = new Pet(null, proprietario, "Max", "2020-01-01", "M", "Cachorro", "Labrador");

        // Configurando o mock para o método save
        when(petRepository.save(pet)).thenReturn(new Pet(1, proprietario, "Max", "2020-01-01", "M", "Cachorro", "Labrador"));

        // Executando o método e verificando os resultados
        Pet saved = petService.salvar(pet);

        assertEquals(1, saved.getId());
        assertEquals("Max", saved.getNome());
        assertEquals("Cachorro", saved.getEspecie());
        assertEquals("Labrador", saved.getRaca());
    }

    @Test
    public void testDeletar() {
        // Simulando a deleção sem exceções
        petService.deletar(1);
    }
}
