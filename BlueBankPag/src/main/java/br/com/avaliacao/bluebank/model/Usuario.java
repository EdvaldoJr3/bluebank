package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.avaliacao.bluebank.dto.ClienteDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Transient;

import br.com.avaliacao.bluebank.enums.Status;

@Entity(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "CLIENTE_ID")
	private Long clienteId;

	@NotNull
	@CPF(message = "*Por Favor digite um CPF válido.")
	@NotEmpty(message = "*O CPF é obrigatório")
	@Column(name = "CPF")
	private String cpf;
	
	@NotNull
	@NotEmpty(message = "*Por favor digite a senha")
	@Column(name = "SENHA")
	@Transient
	private String senha;
	
	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName= "ID",  insertable = false, updatable = false)
	private Cliente cliente;

	public Usuario(){
		
	}

	public Usuario(String cpf, String senha, Status status) {
		super();
		this.cpf = cpf;
		this.senha = senha;
		this.status = status;
	}

    public Usuario(ClienteDTO dto) {
		this.setCpf(dto.getCpf());
		this.setSenha(dto.getSenha());
		this.setDataAlteracao(LocalDateTime.now());
		this.setStatus(Status.ATIVO);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	
	public String getDataAlteracaoFormatada() {
		String dataAlteracaoFormatada = "";
		
		if(dataAlteracao != null){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			
			dataAlteracaoFormatada= dataAlteracao.format(formatter); 
		}
		
		return dataAlteracaoFormatada;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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
		Usuario other = (Usuario) obj;
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

}
