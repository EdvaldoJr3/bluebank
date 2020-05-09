package br.com.avaliacao.bluebank.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClienteDTO {
    @NotBlank(message = "Você precisa digitar seu nome")
    @Size(min = 5, message = "Seu nome precisa estar completo")
    private String nome;

    @NotBlank(message ="Digite seu CPF")
    @CPF(message ="O número de CPF precisa ser válido")
    private String cpf;

    @NotBlank(message ="Digite seu endereço")
    @Size(min = 10, message ="O endereço precisa ser completo")
    private String endereco;

    @NotBlank(message ="Digite seu endereço de email")
    @Email(regexp ="^(.+)@(.+)$", message = "Digite um email válido")
    private String email;

    @NotBlank(message = "Escolha uma agência")
    private Long agenciaId;

    private Long numeroConta;

    @NotBlank(message = "Escolha uma senha")
    @Size(min = 6, max = 6, message = "Escolha uma senha de seis digítos")
    @Pattern(regexp = "^\\d{6}$", message = "Precisa ser uma senha numerica")
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
