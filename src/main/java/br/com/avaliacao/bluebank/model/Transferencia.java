package br.com.avaliacao.bluebank.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.avaliacao.bluebank.enums.StatusTransferencia;

@Entity
public class Transferencia {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "CONTA_ORIGEM_ID")
	private Long contaOrigemId;
	
	@NotNull
	@Column(name = "CONTA_DESTINO_ID")
	private Long contaDestinoId;
	
	@NotNull
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Transient
	private String valorFormatado;

	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private StatusTransferencia statusTransferencia;
	
	@Column(name = "MSG_STATUS")
	private String mensagemStatus;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	@OneToOne
	@JoinColumn(name = "CONTA_ORIGEM_ID", referencedColumnName= "ID", insertable = false, updatable = false)
	private ContaCorrente contaOrigem;
	
	@OneToOne
	@JoinColumn(name = "CONTA_DESTINO_ID", referencedColumnName= "ID", insertable = false, updatable = false)
	private ContaCorrente contaDestino;
	
	@OneToMany(mappedBy = "transferencia")
	private Collection<TransferenciaTransacaoHistorico> transacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaOrigemId() {
		return contaOrigemId;
	}

	public void setContaOrigemId(Long contaOrigemId) {
		this.contaOrigemId = contaOrigemId;
	}

	public Long getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTransferencia getStatusTransferencia() {
		return statusTransferencia;
	}

	public void setStatusTransferencia(StatusTransferencia statusTransferencia) {
		this.statusTransferencia = statusTransferencia;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public ContaCorrente getContaOrigem() {
		if(contaOrigem == null){
			contaOrigem = new ContaCorrente();
		}
		
		return contaOrigem;
	}

	public void setContaOrigem(ContaCorrente contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaCorrente getContaDestino() {
		if(contaDestino == null){
			contaDestino = new ContaCorrente();
		}
		return contaDestino;
	}

	public void setContaDestino(ContaCorrente contaDestino) {
		this.contaDestino = contaDestino;
	}
	
	public String getValorFormatado() {
		if(this.getValor() != null) {
			BigDecimal valor = new BigDecimal (this.getValor().doubleValue());  
			NumberFormat nf = NumberFormat.getCurrencyInstance();  
			valorFormatado = nf.format (valor);
		}
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) throws ParseException {
		
		if(!valorFormatado.isEmpty()){
			
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setGroupingSeparator('.');
			symbols.setDecimalSeparator(',');
			String pattern = "#,##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);
			
			this.setValor((BigDecimal) decimalFormat.parse(valorFormatado));
		}
		
		this.valorFormatado = valorFormatado;
	}
	
	public Collection<TransferenciaTransacaoHistorico> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(Collection<TransferenciaTransacaoHistorico> transacoes) {
		this.transacoes = transacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaDestinoId == null) ? 0 : contaDestinoId.hashCode());
		result = prime * result + ((contaOrigemId == null) ? 0 : contaOrigemId.hashCode());
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
		Transferencia other = (Transferencia) obj;
		if (contaDestinoId == null) {
			if (other.contaDestinoId != null)
				return false;
		} else if (!contaDestinoId.equals(other.contaDestinoId))
			return false;
		if (contaOrigemId == null) {
			if (other.contaOrigemId != null)
				return false;
		} else if (!contaOrigemId.equals(other.contaOrigemId))
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
		return "Transferencia [id=" + id + ", contaOrigemId=" + contaOrigemId + ", contaDestinoId=" + contaDestinoId
				+ ", valor=" + valor + ", statusTransferencia=" + statusTransferencia + ", mensagemStatus="
				+ mensagemStatus + ", dataAlteracao=" + dataAlteracao + ", contaOrigem=" + contaOrigem
				+ ", contaDestino=" + contaDestino + "]";
	}
	
}
