package br.com.alura.gerenciamento_reservas.controller;

import br.com.alura.gerenciamento_reservas.dto.AtualizarReservaDto;
import br.com.alura.gerenciamento_reservas.dto.CriarReservaDto;
import br.com.alura.gerenciamento_reservas.dto.ReservaDto;
import br.com.alura.gerenciamento_reservas.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
   private final ReservaService reservaService;

   public ReservaController(ReservaService reservaService) {
      this.reservaService = reservaService;
   }

   @PostMapping
   public ResponseEntity<ReservaDto> criarReserva(@RequestBody CriarReservaDto dto) {
      ReservaDto reserva = reservaService.criarReserva(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
   }

   @GetMapping
   public ResponseEntity<List<ReservaDto>> listarReservas() {
      List<ReservaDto> reservas = reservaService.listarReservas();
      return ResponseEntity.status(HttpStatus.OK).body(reservas);
   }

   @PatchMapping("/{id}/cancelar")
   public ResponseEntity<Void> cancelar(@PathVariable Long id) {
      reservaService.cancelarReserva(id);
      return ResponseEntity.noContent().build();
   }

   @PutMapping("/{id}")
   public ResponseEntity<ReservaDto> atualizar(
           @PathVariable Long id,
           @RequestBody AtualizarReservaDto dto) {

      ReservaDto reservaAtualizada = reservaService.atualizarReserva(id, dto);

      return ResponseEntity.ok(reservaAtualizada);
   }
}
