package br.com.unisales.meupet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.meupet.repository.VacinacaoRepository;
import br.com.unisales.meupet.table.Vacinacao;

@Service
public class VacinacaoService {
    @Autowired
    private VacinacaoRepository vacinacaoRepository;

    public List<Vacinacao> listarTodos() {
        return vacinacaoRepository.findAll();
    }

    public Optional<Vacinacao> buscarPorId(Integer id) {
        return vacinacaoRepository.findById(id);
    }

    public Vacinacao salvar(Vacinacao vacinacao) {
        return vacinacaoRepository.save(vacinacao);
    }

    public void deletar(Integer id) {
        vacinacaoRepository.deleteById(id);
    }
}
