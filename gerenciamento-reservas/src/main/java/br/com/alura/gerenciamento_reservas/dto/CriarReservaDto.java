package br.com.alura.gerenciamento_reservas.dto;


import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;


public record CriarReservaDto(
        @NotNull
        Long salaId,
        @NotNull
        Long usuarioId,
        @NotNull
        LocalDateTime inicio,
        @NotNull
        LocalDateTime fim )
{
}
