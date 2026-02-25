package br.com.alura.gerenciamento_reservas.dto;

import java.time.LocalDateTime;

public record AtualizarReservaDto(
        LocalDateTime inicio,
        LocalDateTime fim
) {
}
