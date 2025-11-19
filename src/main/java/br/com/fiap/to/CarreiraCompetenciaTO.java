package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

public class CarreiraCompetenciaTO {
    private Long id;
    @NotNull
    private CarreiraTO carreira;
    private String nomeCompetencia;
    private int importancia;
    private char essencial;

    public CarreiraCompetenciaTO() {
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

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public char getEssencial() {
        return essencial;
    }

    public void setEssencial(char essencial) {
        this.essencial = essencial;
    }
}
