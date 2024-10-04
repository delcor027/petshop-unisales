package br.com.unisales.meupet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unisales.meupet.service.VacinacaoService;
import br.com.unisales.meupet.table.Vacinacao;

@RestController
@RequestMapping("/vacinacoes")
public class VacinacaoController {

    @Autowired
    private VacinacaoService vacinacaoService;

    @GetMapping
    public List<Vacinacao> listarTodos() {
        return vacinacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacinacao> buscarPorId(@PathVariable Integer id) {
        Optional<Vacinacao> vacinacao = vacinacaoService.buscarPorId(id);
        return vacinacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vacinacao salvar(@RequestBody Vacinacao vacinacao) {
        return vacinacaoService.salvar(vacinacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Optional<Vacinacao> vacinacao = vacinacaoService.buscarPorId(id);
        if (vacinacao.isPresent()) {
            vacinacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
