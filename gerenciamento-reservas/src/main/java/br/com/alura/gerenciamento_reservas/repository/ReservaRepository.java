package br.com.alura.gerenciamento_reservas.repository;

import br.com.alura.gerenciamento_reservas.model.Reserva;
import br.com.alura.gerenciamento_reservas.model.StatusReserva;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva,Long>{

    List<Reserva> findBySalaIdAndStatus( Long salaId, StatusReserva status);
}
