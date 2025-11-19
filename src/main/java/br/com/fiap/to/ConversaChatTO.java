package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ConversaChatTO {
    private Long id;
    @NotNull
    private UsuarioTO usuario;
    private String mensagemUser;
    private String respostaBot;
    private String contexto;
    private LocalDate dataEnvio;

    public ConversaChatTO() {
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

    public String getMensagemUser() {
        return mensagemUser;
    }

    public void setMensagemUser(String mensagemUser) {
        this.mensagemUser = mensagemUser;
    }

    public String getRespostaBot() {
        return respostaBot;
    }

    public void setRespostaBot(String respostaBot) {
        this.respostaBot = respostaBot;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
