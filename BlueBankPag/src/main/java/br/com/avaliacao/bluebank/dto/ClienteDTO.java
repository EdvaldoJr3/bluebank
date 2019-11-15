package br.com.avaliacao.bluebank.dto;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private Long agenciaId;
    private Long numeroConta;
    private String senha;

    private Long digitoConta;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(Long agenciaId) {
        this.agenciaId = agenciaId;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDigitoConta(Long digitoConta) {
        this.digitoConta = digitoConta;
    }

    public Long getDigitoConta() {
        return digitoConta;
    }
}
