package br.com.unisales.meupet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.meupet.repository.ProprietarioRepository;
import br.com.unisales.meupet.table.Proprietario;

@Service
public class ProprietarioService {
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public List<Proprietario> listarTodos() {
        //return proprietarioRepository.findByAtivo((byte) 1); // Retorna apenas ativos
        return proprietarioRepository.findAll();
    }

    public Optional<Proprietario> buscarPorId(Integer id) {
        return proprietarioRepository.findById(id);
    }

    public Proprietario salvar(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    public void ativar(Integer id) {
        Proprietario proprietario = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        proprietario.setAtivo((byte) 1); // Define "ativo" como 1 (ativo)
        proprietarioRepository.save(proprietario);
    }
    

    public void inativar(Integer id) {
        Proprietario proprietario = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        proprietario.setAtivo((byte) 0); // Define "ativo" como 0
        proprietarioRepository.save(proprietario);
    }
}
