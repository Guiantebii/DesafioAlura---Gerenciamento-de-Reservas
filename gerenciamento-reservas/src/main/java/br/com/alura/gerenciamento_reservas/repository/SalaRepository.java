package br.com.alura.gerenciamento_reservas.repository;

import br.com.alura.gerenciamento_reservas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository  extends JpaRepository<Sala, Long> {
}
