package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CarreiraTO {
    private Long id;
    @NotBlank
    private String nomeCarreira;
    private String descricao;
    private int valorCrescimento;
    private int valorSalarioMedio;
    private LocalDate dataEmergenteDesde;
    private LocalDate dataAtualizacao;
    private char ativo;

    public CarreiraTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCarreira() {
        return nomeCarreira;
    }

    public void setNomeCarreira(String nomeCarreira) {
        this.nomeCarreira = nomeCarreira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValorCrescimento() {
        return valorCrescimento;
    }

    public void setValorCrescimento(int valorCrescimento) {
        this.valorCrescimento = valorCrescimento;
    }

    public int getValorSalarioMedio() {
        return valorSalarioMedio;
    }

    public void setValorSalarioMedio(int valorSalarioMedio) {
        this.valorSalarioMedio = valorSalarioMedio;
    }

    public LocalDate getDataEmergenteDesde() {
        return dataEmergenteDesde;
    }

    public void setDataEmergenteDesde(LocalDate dataEmergenteDesde) {
        this.dataEmergenteDesde = dataEmergenteDesde;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
