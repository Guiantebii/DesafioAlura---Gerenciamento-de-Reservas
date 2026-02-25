package br.com.alura.gerenciamento_reservas.service;

import br.com.alura.gerenciamento_reservas.dto.AtualizarSalaDto;
import br.com.alura.gerenciamento_reservas.dto.CadastrarSalaDto;
import br.com.alura.gerenciamento_reservas.dto.SalaDto;
import br.com.alura.gerenciamento_reservas.exception.ValidacaoException;
import br.com.alura.gerenciamento_reservas.model.Sala;
import br.com.alura.gerenciamento_reservas.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public SalaDto cadastrarSala(CadastrarSalaDto dto){
        Sala sala = new Sala();
        sala.setNome(dto.nome());
        sala.setCapacidade(dto.capacidade());
        sala.setAtiva(Boolean.TRUE);
        Sala salaSalva = salaRepository.save(sala);


        return new SalaDto(
                salaSalva.getId(),
                salaSalva.getNome(),
                salaSalva.getCapacidade(),
                salaSalva.isAtiva()
        );
    }
    public List<SalaDto> listarSala(){
        List<Sala> salas = salaRepository.findAll();
        return salas.stream().map(
                sala -> new SalaDto(
                        sala.getId(),
                        sala.getNome(),
                        sala.getCapacidade(),
                        sala.isAtiva()
                )).toList();
    }

    public SalaDto atualizarSala(Long id,AtualizarSalaDto dto){
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Sala não encontrada"));

        sala.setNome(dto.nome());
        sala.setCapacidade(dto.capacidade());

        Sala salaSalva = salaRepository.save(sala);

        return new SalaDto(
                salaSalva.getId(),
                salaSalva.getNome(),
                salaSalva.getCapacidade(),
                salaSalva.isAtiva()
        );
    }

    public void desativarSala(Long id){
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Sala não encontrada"));
        if (!sala.isAtiva()) {
            throw new ValidacaoException("Sala já está desativada");
        }

        sala.setAtiva(Boolean.FALSE);
        salaRepository.save(sala);
    }
}

