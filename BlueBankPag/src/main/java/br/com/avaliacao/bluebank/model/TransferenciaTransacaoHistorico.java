package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "TRANSFERENCIA_TRANSACAO_HISTORICO")
public class TransferenciaTransacaoHistorico {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "TRANSFERENCIA_ID")
	private Long transferenciaId;
	
	@NotNull
	@Column(name = "TRANSACAO_HISTORICO_ID")
	private Long transacaoHistoricoId;
	
	@NotNull
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
	
	@OneToOne
	@JoinColumn(name = "TRANSFERENCIA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Transferencia transferencia;
	
	@OneToOne
	@JoinColumn(name = "TRANSACAO_HISTORICO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private TransacaoHistorico transacaoHistorico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransferenciaId() {
		return transferenciaId;
	}

	public void setTransferenciaId(Long transferenciaId) {
		this.transferenciaId = transferenciaId;
	}

	public Long getTransacaoHistoricoId() {
		return transacaoHistoricoId;
	}

	public void setTransacaoHistoricoId(Long transacaoHistoricoId) {
		this.transacaoHistoricoId = transacaoHistoricoId;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}

	public TransacaoHistorico getTransacaoHistorico() {
		return transacaoHistorico;
	}

	public void setTransacaoHistorico(TransacaoHistorico transacaoHistorico) {
		this.transacaoHistorico = transacaoHistorico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((transacaoHistoricoId == null) ? 0 : transacaoHistoricoId.hashCode());
		result = prime * result + ((transferenciaId == null) ? 0 : transferenciaId.hashCode());
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
		TransferenciaTransacaoHistorico other = (TransferenciaTransacaoHistorico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (transacaoHistoricoId == null) {
			if (other.transacaoHistoricoId != null)
				return false;
		} else if (!transacaoHistoricoId.equals(other.transacaoHistoricoId))
			return false;
		if (transferenciaId == null) {
			if (other.transferenciaId != null)
				return false;
		} else if (!transferenciaId.equals(other.transferenciaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransferenciaTransacaoHistorico [id=" + id + ", transferenciaId=" + transferenciaId
				+ ", transacaoHistoricoId=" + transacaoHistoricoId + ", dataAlteracao=" + dataAlteracao
				+ ", transferencia=" + transferencia + ", transacaoHistorico=" + transacaoHistorico + "]";
	}
}
