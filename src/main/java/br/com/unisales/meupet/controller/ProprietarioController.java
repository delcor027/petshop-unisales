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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Optional<Proprietario> proprietario = proprietarioService.buscarPorId(id);
        if (proprietario.isPresent()) {
            proprietarioService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
