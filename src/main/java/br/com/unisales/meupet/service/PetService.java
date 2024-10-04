package br.com.unisales.meupet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.meupet.table.Pet;
import br.com.unisales.meupet.repository.PetRepository;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Optional<Pet> buscarPorId(Integer id) {
        return petRepository.findById(id);
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletar(Integer id) {
        petRepository.deleteById(id);
    }
}
