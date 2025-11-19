package br.com.fiap.to;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class AcaoAprendizTO {
    private Long id;
    @NotNull
    private UsuarioTO usuario;
    private String tipoAcao;
    private String acao;
    private String  nomeCompetenciaAlvo;
    private int valorPrioridade;
    private String status;
    @PastOrPresent
    private LocalDate dataCriacao;
    @FutureOrPresent
    private LocalDate dataConclusao;

    public AcaoAprendizTO() {
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

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getNomeCompetenciaAlvo() {
        return nomeCompetenciaAlvo;
    }

    public void setNomeCompetenciaAlvo(String nomeCompetenciaAlvo) {
        this.nomeCompetenciaAlvo = nomeCompetenciaAlvo;
    }

    public int getValorPrioridade() {
        return valorPrioridade;
    }

    public void setValorPrioridade(int valorPrioridade) {
        this.valorPrioridade = valorPrioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
