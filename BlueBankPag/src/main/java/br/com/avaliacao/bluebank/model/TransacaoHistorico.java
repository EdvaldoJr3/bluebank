package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.avaliacao.bluebank.enums.StatusTransferencia;

@Entity
public class TransacaoHistorico {
	
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
	@Column(name = "DATA_OPERACAO")
	private LocalDateTime dataOperacao;
	
	@NotNull
	@Column(name = "TIPO_OPERACAO_ID")
	private Long tipoOperacaoId;

	@NotNull
	@Column(name = "VALOR")
	private Double valor;

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
	@JoinColumn(name = "CONTA_ORIGEM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private ContaCorrente contaOrigem;

	@OneToOne
	@JoinColumn(name = "CONTA_DESTINO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private ContaCorrente contaDestino;
	
	@OneToOne
	@JoinColumn(name = "TIPO_OPERACAO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private TipoOperacao tipoOperacao;

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

	public Long getTipoOperacaoId() {
		return tipoOperacaoId;
	}

	public void setTipoOperacaoId(Long tipoOperacaoId) {
		this.tipoOperacaoId = tipoOperacaoId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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
		return contaOrigem;
	}

	public void setContaOrigem(ContaCorrente contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaCorrente getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaCorrente contaDestino) {
		this.contaDestino = contaDestino;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	

	public LocalDateTime getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(LocalDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
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
		TransacaoHistorico other = (TransacaoHistorico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransacaoHistorico [id=" + id + ", contaOrigemId=" + contaOrigemId + ", contaDestinoId="
				+ contaDestinoId + ", dataOperacao=" + dataOperacao + ", tipoOperacaoId=" + tipoOperacaoId + ", valor="
				+ valor + ", statusTransferencia=" + statusTransferencia + ", mensagemStatus=" + mensagemStatus
				+ ", dataAlteracao=" + dataAlteracao + ", contaOrigem=" + contaOrigem + ", contaDestino=" + contaDestino
				+ ", tipoOperacao=" + tipoOperacao + "]";
	}
	
}
