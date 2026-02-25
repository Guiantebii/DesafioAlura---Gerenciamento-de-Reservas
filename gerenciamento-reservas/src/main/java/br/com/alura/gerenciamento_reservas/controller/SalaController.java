package br.com.alura.gerenciamento_reservas.controller;

import br.com.alura.gerenciamento_reservas.dto.AtualizarSalaDto;
import br.com.alura.gerenciamento_reservas.dto.CadastrarSalaDto;
import br.com.alura.gerenciamento_reservas.dto.SalaDto;
import br.com.alura.gerenciamento_reservas.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<SalaDto> cadastrarSala(@RequestBody CadastrarSalaDto Dto) {
        SalaDto sala = salaService.cadastrarSala(Dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sala);
    }

    @GetMapping
    public ResponseEntity<List<SalaDto>> listarSalas() {
        List<SalaDto> salas =  salaService.listarSala();
        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }

    @PutMapping("{id}")
    public ResponseEntity<SalaDto> atualizarSala(@PathVariable  Long id,@RequestBody AtualizarSalaDto dto) {
        SalaDto sala = salaService.atualizarSala(id,dto);
        return ResponseEntity.ok(sala);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> desativarSala(@PathVariable  Long id) {
        salaService.desativarSala(id);
        return ResponseEntity.noContent().build();
    }
}
