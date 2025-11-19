package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MatchCarreiraTO {
    private Long id;
    @NotNull
    private UsuarioTO usuario;
    private CarreiraTO carreira;
    private int valorCompatibilidade;
    private String gaps;
    private LocalDate dataCalculo;

    public MatchCarreiraTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public CarreiraTO getCarreira() {
        return carreira;
    }

    public void setCarreira(CarreiraTO carreira) {
        this.carreira = carreira;
    }

    public int getValorCompatibilidade() {
        return valorCompatibilidade;
    }

    public void setValorCompatibilidade(int valorCompatibilidade) {
        this.valorCompatibilidade = valorCompatibilidade;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDate dataCalculo) {
        this.dataCalculo = dataCalculo;
    }
}
