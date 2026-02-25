package br.com.alura.gerenciamento_reservas.controller;

import br.com.alura.gerenciamento_reservas.dto.AtualizarUsuarioDto;
import br.com.alura.gerenciamento_reservas.dto.CadastrarUsuarioDto;
import br.com.alura.gerenciamento_reservas.dto.UsuarioDto;
import br.com.alura.gerenciamento_reservas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {

        List<UsuarioDto> usuarios = usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuarios);
    }
    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody CadastrarUsuarioDto dto) {
        UsuarioDto usuarioDto = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(201).body(usuarioDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id,@RequestBody AtualizarUsuarioDto dto) {
        UsuarioDto usuario = usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(usuario);
    }
}
