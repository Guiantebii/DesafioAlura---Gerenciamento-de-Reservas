package br.com.alura.gerenciamento_reservas.repository;

import br.com.alura.gerenciamento_reservas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
