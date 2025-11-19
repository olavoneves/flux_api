package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CompetenciaTO {
    private Long id;
    @NotNull
    private UsuarioTO usuario;
    private String nomeCompetencia;
    private int valorProficiencia;
    private String tipoVerificacao;
    private String provaUrl;
    private LocalDate dataVerificacao;
    private LocalDate dataUltimoUso;
    private int valorDecayDiario;
    private char ativo;

    public CompetenciaTO() {
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

    public String getNomeCompetencia() {
        return nomeCompetencia;
    }

    public void setNomeCompetencia(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia;
    }

    public int getValorProficiencia() {
        return valorProficiencia;
    }

    public void setValorProficiencia(int valorProficiencia) {
        this.valorProficiencia = valorProficiencia;
    }

    public String getTipoVerificacao() {
        return tipoVerificacao;
    }

    public void setTipoVerificacao(String tipoVerificacao) {
        this.tipoVerificacao = tipoVerificacao;
    }

    public String getProvaUrl() {
        return provaUrl;
    }

    public void setProvaUrl(String provaUrl) {
        this.provaUrl = provaUrl;
    }

    public LocalDate getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(LocalDate dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
    }

    public LocalDate getDataUltimoUso() {
        return dataUltimoUso;
    }

    public void setDataUltimoUso(LocalDate dataUltimoUso) {
        this.dataUltimoUso = dataUltimoUso;
    }

    public int getValorDecayDiario() {
        return valorDecayDiario;
    }

    public void setValorDecayDiario(int valorDecayDiario) {
        this.valorDecayDiario = valorDecayDiario;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
