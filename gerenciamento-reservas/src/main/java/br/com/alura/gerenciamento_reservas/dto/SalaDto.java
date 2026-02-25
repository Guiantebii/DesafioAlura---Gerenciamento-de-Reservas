package br.com.alura.gerenciamento_reservas.dto;

public record SalaDto(
        Long id,
        String nome,
        Integer capacidade,
        Boolean ativa
) {
}
