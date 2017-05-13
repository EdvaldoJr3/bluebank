package br.com.avaliacao.bluebank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.avaliacao.bluebank.enums.StatusTransferencia;

@Entity(name = "VIEW_HISTORICO_TRANSACAO")
public class ViewHistoricoTransacao {
	
	@Id
	private Long id;
	
	@Column(name = "CONTA_ID")
	private Long contaId;
	
	@Column(name = "TIPO_OPERACAO_ID")
	private Long tipoOperacaoId;
	
	@Column(name = "DATA_OPERACAO")
	private LocalDateTime dataOperacao;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private StatusTransferencia statusTransferencia;
	
	@Column(name = "MSG_STATUS")
	private String mensagemStatus;
	
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getTipoOperacaoId() {
		return tipoOperacaoId;
	}

	public void setTipoOperacaoId(Long tipoOperacaoId) {
		this.tipoOperacaoId = tipoOperacaoId;
	}

	public LocalDateTime getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(LocalDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaId == null) ? 0 : contaId.hashCode());
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
		ViewHistoricoTransacao other = (ViewHistoricoTransacao) obj;
		if (contaId == null) {
			if (other.contaId != null)
				return false;
		} else if (!contaId.equals(other.contaId))
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
		return "ViewHistoricoTransacao [id=" + id + ", contaId=" + contaId + ", tipoOperacaoId=" + tipoOperacaoId
				+ ", dataOperacao=" + dataOperacao + ", statusTransferencia=" + statusTransferencia
				+ ", mensagemStatus=" + mensagemStatus + ", dataAlteracao=" + dataAlteracao + "]";
	}

}
