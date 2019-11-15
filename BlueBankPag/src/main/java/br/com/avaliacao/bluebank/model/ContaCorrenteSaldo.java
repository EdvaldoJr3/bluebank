package br.com.avaliacao.bluebank.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity(name = "CONTA_CORRENTE_SALDO")
public class ContaCorrenteSaldo {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Column(name = "CONTA_CORRENTE_ID")
	private Long contaCorrenteId;
	
	@NotNull
	@Column(name = "SALDO")
	private BigDecimal valor;
	
	@NotNull
	@Version
	@Column(name = "VERSAO")
	private Long versao;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	@OneToOne
	@JoinColumn(name = "CONTA_CORRENTE_ID", referencedColumnName= "ID", insertable = false, updatable = false)
	private ContaCorrente contaCorrente;
	
	@Transient
	private  String valorFormatado;
	
	public ContaCorrenteSaldo() {
		this.setValor(new BigDecimal(10));
		this.setVersao(1L);
		this.setDataAlteracao(LocalDateTime.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaCorrenteId() {
		return contaCorrenteId;
	}

	public void setContaCorrenteId(Long contaCorrenteId) {
		this.contaCorrenteId = contaCorrenteId;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public String getValorFormatado(){
		
		if(this.getValor() != null) {
			BigDecimal valor = new BigDecimal (this.getValor().doubleValue());  
			NumberFormat nf = NumberFormat.getCurrencyInstance();  
			valorFormatado = nf.format (valor);
		} else {
			this.setValorFormatado("0,00");
		}
		return this.valorFormatado;
	}
	
	public void setValorFormatado(String valorFormatado){
		this.valorFormatado = valorFormatado;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaCorrenteId == null) ? 0 : contaCorrenteId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
		ContaCorrenteSaldo other = (ContaCorrenteSaldo) obj;
		if (contaCorrenteId == null) {
			if (other.contaCorrenteId != null)
				return false;
		} else if (!contaCorrenteId.equals(other.contaCorrenteId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}
	
}
