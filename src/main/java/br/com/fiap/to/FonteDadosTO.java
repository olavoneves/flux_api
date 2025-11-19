package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FonteDadosTO {
    private Long id;
    @NotNull
    private UsuarioTO usuario;
    private String fonte;
    private String identificador;
    private LocalDate dataConexao;
    private LocalDate dataUltimaSync;
    private char ativo;

    public FonteDadosTO() {
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

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getDataConexao() {
        return dataConexao;
    }

    public void setDataConexao(LocalDate dataConexao) {
        this.dataConexao = dataConexao;
    }

    public LocalDate getDataUltimaSync() {
        return dataUltimaSync;
    }

    public void setDataUltimaSync(LocalDate dataUltimaSync) {
        this.dataUltimaSync = dataUltimaSync;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
