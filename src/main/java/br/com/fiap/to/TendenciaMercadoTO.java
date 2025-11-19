package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TendenciaMercadoTO {
    private Long id;
    @NotNull
    private CarreiraTO carreira;
    private String nomeCompetencia;
    private int valorDemanda;
    private int valorCrescimentoTrintaDias;
    private LocalDate dataReferencia;
    private LocalDate dataCalculo;

    public TendenciaMercadoTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarreiraTO getCarreira() {
        return carreira;
    }

    public void setCarreira(CarreiraTO carreira) {
        this.carreira = carreira;
    }

    public String getNomeCompetencia() {
        return nomeCompetencia;
    }

    public void setNomeCompetencia(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    public int getValorDemanda() {
        return valorDemanda;
    }

    public void setValorDemanda(int valorDemanda) {
        this.valorDemanda = valorDemanda;
    }

    public int getValorCrescimentoTrintaDias() {
        return valorCrescimentoTrintaDias;
    }

    public void setValorCrescimentoTrintaDias(int valorCrescimentoTrintaDias) {
        this.valorCrescimentoTrintaDias = valorCrescimentoTrintaDias;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDate dataCalculo) {
        this.dataCalculo = dataCalculo;
    }
}
