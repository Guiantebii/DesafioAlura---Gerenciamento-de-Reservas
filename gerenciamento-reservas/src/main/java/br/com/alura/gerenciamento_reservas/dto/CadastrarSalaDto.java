package br.com.alura.gerenciamento_reservas.dto;

public record CadastrarSalaDto(
        Long id,
        String nome,
        int capacidade
) {
}
