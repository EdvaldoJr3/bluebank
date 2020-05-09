package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.avaliacao.bluebank.dto.ClienteDTO;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.avaliacao.bluebank.enums.Status;

@Entity
@Table(name = "CONTA_CORRENTE")
public class ContaCorrente {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	@Column(name = "CLIENTE_ID")
	private Long clienteId;
	
	@NotNull
	@Column(name = "AGENCIA_ID")
	private Long agenciaId;
	
	@NotNull(message = "*Por favor informe o número da conta")
	@Column(name = "NUMERO")
	private Long numero;
	
	@NotNull(message = "*Por favor informe o dígito da conta")
	@Column(name = "DIGITO")
	private Long digito;

	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	@OneToOne(mappedBy="contaCorrente")
	private ContaCorrenteSaldo saldo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName= "ID", insertable = false, updatable = false)
	private Cliente cliente;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "AGENCIA_ID", referencedColumnName= "ID", insertable = false, updatable = false)
	private Agencia agencia;
	
	public ContaCorrente(Long numero, Long digito) {
		this.numero = numero;
		this.digito = digito;
		this.status = Status.ATIVO;
	}
	
	public ContaCorrente() {

	}

	public ContaCorrente(ClienteDTO dto) {
		this.setAgenciaId(dto.getAgenciaId());
		this.setNumero(dto.getNumeroConta());
		this.setDataAlteracao(LocalDateTime.now());
		this.setStatus(Status.ATIVO);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getAgenciaId() {
		return agenciaId;
	}

	public void setAgenciaId(Long agenciaId) {
		this.agenciaId = agenciaId;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	public Long getDigito() {
		return digito;
	}

	public void setDigito(Long digito) {
		this.digito = digito;
	}
	
	public ContaCorrenteSaldo getSaldo() {
		return saldo;
	}

	public void setSaldo(ContaCorrenteSaldo saldo) {
		this.saldo = saldo;
	}
	
	public boolean exists(){
		return (id != null && id > 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agenciaId == null) ? 0 : agenciaId.hashCode());
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
		result = prime * result + ((digito == null) ? 0 : digito.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		ContaCorrente other = (ContaCorrente) obj;
		if (agenciaId == null) {
			if (other.agenciaId != null)
				return false;
		} else if (!agenciaId.equals(other.agenciaId))
			return false;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		if (digito == null) {
			if (other.digito != null)
				return false;
		} else if (!digito.equals(other.digito))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContaCorrente [id=" + id + ", clienteId=" + clienteId + ", agenciaId=" + agenciaId + ", numero="
				+ numero + ", digito=" + digito + ", status=" + status + ", dataAlteracao=" + dataAlteracao
				+ ", cliente=" + cliente + ", agencia=" + agencia + "]";
	}
}
