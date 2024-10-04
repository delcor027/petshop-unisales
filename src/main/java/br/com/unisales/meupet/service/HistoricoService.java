package br.com.unisales.meupet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.meupet.repository.HistoricoRepository;
import br.com.unisales.meupet.table.Historico;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarTodos() {
        return historicoRepository.findAll();
    }

    public Optional<Historico> buscarPorId(Integer id) {
        return historicoRepository.findById(id);
    }

    public Historico salvar(Historico historico) {
        return historicoRepository.save(historico);
    }

    public void deletar(Integer id) {
        historicoRepository.deleteById(id);
    }
}
