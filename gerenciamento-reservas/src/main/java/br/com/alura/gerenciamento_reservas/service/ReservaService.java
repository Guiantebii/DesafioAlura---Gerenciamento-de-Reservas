package br.com.alura.gerenciamento_reservas.service;

import br.com.alura.gerenciamento_reservas.dto.AtualizarReservaDto;
import br.com.alura.gerenciamento_reservas.dto.CriarReservaDto;
import br.com.alura.gerenciamento_reservas.dto.ReservaDto;
import br.com.alura.gerenciamento_reservas.exception.ValidacaoException;
import br.com.alura.gerenciamento_reservas.model.Reserva;
import br.com.alura.gerenciamento_reservas.model.Sala;
import br.com.alura.gerenciamento_reservas.model.StatusReserva;
import br.com.alura.gerenciamento_reservas.model.Usuario;
import br.com.alura.gerenciamento_reservas.repository.ReservaRepository;
import br.com.alura.gerenciamento_reservas.repository.SalaRepository;
import br.com.alura.gerenciamento_reservas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;

    public ReservaService(SalaRepository salaRepository,
                          UsuarioRepository usuarioRepository,
                          ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
    }


    public ReservaDto criarReserva(CriarReservaDto criarReservaDto) {
        //Buscar sala
        Sala sala = salaRepository.findById(criarReservaDto.salaId()).orElseThrow(() -> new ValidacaoException("Sala não encontrada"));

        if(!sala.isAtiva()){
            throw new ValidacaoException("Sala está inativa");
        }
        //Buscar usuario
        Usuario usuario = usuarioRepository.findById(criarReservaDto.usuarioId()).orElseThrow(() -> new ValidacaoException("Usuario não encontrado"));

        if(!criarReservaDto.inicio().isBefore(criarReservaDto.fim())){
            throw new ValidacaoException("Data de inicio deve ser antes da data do fim");
        }

        List<Reserva> reservaAtivas = reservaRepository.findBySalaIdAndStatus(criarReservaDto.salaId(), StatusReserva.ATIVA);

        for(Reserva existente : reservaAtivas){
            boolean conflito = criarReservaDto.inicio().isBefore(existente.getDataFim()) &&
                    existente.getDataInicio().isBefore(criarReservaDto.fim());

            if(conflito) {
                throw new ValidacaoException("Conflito de horário com outra reserva");
            }
        }
        Reserva reserva = new Reserva(
                sala,
                usuario,
                criarReservaDto.inicio(),
                criarReservaDto.fim()
        );
        return ReservaDto.fromEntity(reservaRepository.save(reserva));

    }

    public List<ReservaDto> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(
                reserva -> new ReservaDto(
                        reserva.getId(),
                        reserva.getSala(),
                        reserva.getUsuario(),
                        reserva.getStatus(),
                        reserva.getDataInicio(),
                        reserva.getDataFim()
                )).toList();
    }

    public ReservaDto atualizarReserva(Long id, AtualizarReservaDto dto) {
            Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new ValidacaoException("Reserva não encontrada"));

            if(reserva.getStatus() != (StatusReserva.ATIVA)){
                throw new ValidacaoException("Não é possível alterar uma reserva cancelada");
            }

        if (!dto.inicio().isBefore(dto.fim())) {
            throw new ValidacaoException("Data de início deve ser antes da data de fim");
        }
        List <Reserva> reservasAtivas = reservaRepository.findBySalaIdAndStatus(reserva.getSala().getId(), StatusReserva.ATIVA);
        for(Reserva existente : reservasAtivas){
            if(existente.getId().equals(reserva.getId())){
                continue;
            }
            boolean conflito =
                    dto.inicio().isBefore(existente.getDataFim()) &&
                            existente.getDataInicio().isBefore(dto.fim());
            if (conflito) {
                throw new ValidacaoException("Conflito de horário com outra reserva");
            }


        }
        reserva.setDataInicio(dto.inicio());
        reserva.setDataFim(dto.fim());

        reservaRepository.save(reserva);

        return ReservaDto.fromEntity(reserva);
    }

    public void cancelarReserva(Long id) {
        //Buscar Reserva
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if(reserva.getStatus() == (StatusReserva.CANCELADA)){
            throw new ValidacaoException("Reserva já esta cancelada");
        }
        reserva.setStatus(StatusReserva.CANCELADA);

        reservaRepository.save(reserva);
    }
}
