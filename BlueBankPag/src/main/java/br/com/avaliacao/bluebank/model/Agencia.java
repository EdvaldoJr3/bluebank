package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.avaliacao.bluebank.enums.Status;

@Entity
public class Agencia {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	@NotEmpty(message = "*Por favor informe o número da agência")
	@Column(name = "NUMERO")
	private Long numero;
	
	@NotNull
	@NotEmpty(message = "*Por favor informe o dígito da agência")
	@Column(name = "DIGITO")
	private Long digito;
	
	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	public Agencia() {
		
	}
	
	public Agencia(Long numero, Long digito) {
		this.numero = numero;
		this.digito = digito;
		this.status = Status.ATIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Long getDigito() {
		return digito;
	}

	public void setDigito(Long digito) {
		this.digito = digito;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Agencia other = (Agencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", numero=" + numero + ", digito=" + digito + ", status=" + status
				+ ", dataAlteracao=" + dataAlteracao + "]";
	}
	
}
