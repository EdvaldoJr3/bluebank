package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.avaliacao.bluebank.dto.ClienteDTO;
import br.com.avaliacao.bluebank.enums.Status;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@NotNull
	@NotEmpty
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@NotEmpty
	@Column(name = "CPF")
	private String cpf;

	@NotNull
	@NotEmpty
	@Column(name = "ENDERECO")
	private String endereco;

	@NotNull
	@NotEmpty
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
	private Status status;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", referencedColumnName= "CLIENTE_ID",  insertable = false, updatable = false)
	private ContaCorrente contaCorrente;

	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "ID", referencedColumnName= "CLIENTE_ID",  insertable = false, updatable = false)
	private Usuario usuario	;

	public Cliente() {

	}

	public Cliente(ClienteDTO dto) {
		this.setNome(dto.getNome());
		this.setEndereco(dto.getEndereco());
		this.setCpf(dto.getCpf());
		this.setEmail(dto.getEmail());
		this.setStatus(Status.ATIVO);
		this.setDataAlteracao(LocalDateTime.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", email=" + email
				+ ", status=" + status + ", dataAlteracao=" + dataAlteracao + "]";
	}
	
	
}
