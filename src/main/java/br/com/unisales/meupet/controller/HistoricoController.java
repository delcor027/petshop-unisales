package br.com.unisales.meupet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unisales.meupet.service.HistoricoService;
import br.com.unisales.meupet.table.Historico;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public List<Historico> listarTodos() {
        return historicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> buscarPorId(@PathVariable Integer id) {
        Optional<Historico> historico = historicoService.buscarPorId(id);
        return historico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Historico salvar(@RequestBody Historico historico) {
        return historicoService.salvar(historico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Optional<Historico> historico = historicoService.buscarPorId(id);
        if (historico.isPresent()) {
            historicoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
