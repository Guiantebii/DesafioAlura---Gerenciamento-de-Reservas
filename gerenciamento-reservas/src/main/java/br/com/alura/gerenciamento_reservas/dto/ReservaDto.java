package br.com.alura.gerenciamento_reservas.dto;

import br.com.alura.gerenciamento_reservas.model.Reserva;
import br.com.alura.gerenciamento_reservas.model.Sala;
import br.com.alura.gerenciamento_reservas.model.StatusReserva;
import br.com.alura.gerenciamento_reservas.model.Usuario;

import java.time.LocalDateTime;

public record ReservaDto(Long id,
                         Sala sala,
                         Usuario usuario,
                         StatusReserva status,
                         LocalDateTime dataInicio,
                         LocalDateTime dataFim) {

    public static ReservaDto fromEntity(Reserva reserva) {
        return new ReservaDto(
                reserva.getId(),
                reserva.getSala(),
                reserva.getUsuario(),
                reserva.getStatus(),
                reserva.getDataInicio(),
                reserva.getDataFim()
        );
    }
}
