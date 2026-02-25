package br.com.alura.gerenciamento_reservas.model;

import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private Sala sala;
    private Usuario usuario;
    private StatusReserva status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;


    public Reserva(
            Sala sala,
            Usuario usuario,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        this.sala = sala;
        this.usuario = usuario;
        this.dataInicio = inicio;
        this.dataFim = fim;
        this.status = StatusReserva.ATIVA;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
