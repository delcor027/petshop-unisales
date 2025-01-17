package br.com.unisales.meupet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unisales.meupet.service.ProprietarioService;
import br.com.unisales.meupet.table.Proprietario;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @GetMapping
    public List<Proprietario> listarTodos() {
        return proprietarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscarPorId(@PathVariable Integer id) {
        Optional<Proprietario> proprietario = proprietarioService.buscarPorId(id);
        return proprietario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proprietario salvar(@RequestBody Proprietario proprietario) {
        return proprietarioService.salvar(proprietario);
    }

    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativar(@PathVariable Integer id) {
        Optional<Proprietario> proprietario = proprietarioService.buscarPorId(id);
        if (proprietario.isPresent()) {
            proprietarioService.ativar(id); // Altera o campo "ativo" para 1
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Integer id) {
        Optional<Proprietario> proprietario = proprietarioService.buscarPorId(id);
        if (proprietario.isPresent()) {
            proprietarioService.inativar(id); // Altera o campo "ativo" para 0
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
