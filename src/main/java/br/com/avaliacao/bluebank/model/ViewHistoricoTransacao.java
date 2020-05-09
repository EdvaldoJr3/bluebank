package br.com.avaliacao.bluebank.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.avaliacao.bluebank.enums.StatusTransferencia;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("select\n" +
		"    th.id AS ID, th.conta_origem_id AS CONTA_ID, th.tipo_operacao_id AS TIPO_OPERACAO_ID,\n" +
		"    th.data_operacao AS DATA_OPERACAO, th.valor AS VALOR, th.status AS STATUS,\n" +
		"    cli.cpf as CPF_OPERACAO, cli.nome as NOME_CLIENTE_OPERACAO, ag.numero as AGENCIA_NUMERO,\n" +
		"    ag.digito as AGENCIA_DIGITO, conta.numero as CONTA_NUMERO, conta.digito as CONTA_DIGITO,\n" +
		"    th.msg_status AS MSG_STATUS, th.data_alteracao AS DATA_ALTERACAO, 'Débito' AS DESCRICAO_OPERACAO\n" +
		"from\n" +
		"    transacao_historico as th, transferencia_transacao_historico as tth,\n" +
		"    transferencia as transf, conta_corrente as conta,\n" +
		"    cliente cli, agencia as ag\n" +
		"where th.id = tth.transacao_historico_id\n" +
		"and transf.id = tth.transferencia_id\n" +
		"and transf.conta_destino_id = conta.id\n" +
		"and cli.id = conta.cliente_id\n" +
		"and conta.agencia_id = ag.id\n" +
		"and th.tipo_operacao_id = 2\n" +
		"UNION\n" +
		"select\n" +
		"    th.id AS ID, th.conta_destino_id AS CONTA_ID, th.tipo_operacao_id AS TIPO_OPERACAO_ID,\n" +
		"    th.data_operacao AS DATA_OPERACAO, th.valor AS VALOR, th.status AS STATUS,\n" +
		"    cli.cpf as CPF_OPERACAO, cli.nome as NOME_CLIENTE_OPERACAO, ag.numero as AGENCIA_NUMERO,\n" +
		"    ag.digito as AGENCIA_DIGITO, conta.numero as CONTA_NUMERO, conta.digito as CONTA_DIGITO,\n" +
		"    th.msg_status AS MSG_STATUS, th.data_alteracao AS DATA_ALTERACAO, 'Crédito' AS DESCRICAO_OPERACAO\n" +
		"from\n" +
		"    transacao_historico as th, transferencia_transacao_historico as tth,\n" +
		"    transferencia as transf, conta_corrente as conta,\n" +
		"    cliente cli, agencia as ag\n" +
		"where th.id = tth.transacao_historico_id\n" +
		"and transf.id = tth.transferencia_id\n" +
		"and transf.conta_origem_id = conta.id\n" +
		"and cli.id = conta.cliente_id\n" +
		"and conta.agencia_id = ag.id\n" +
		"and th.tipo_operacao_id = 1\n")
public class ViewHistoricoTransacao {

	@Id
	private Long id;

	@Column(name = "CONTA_ID")
	private Long contaId;

	@Column(name = "TIPO_OPERACAO_ID")
	private Long tipoOperacaoId;

	@Column(name = "DATA_OPERACAO")
	private LocalDateTime dataOperacao;

	@Column(name = "VALOR")
	private BigDecimal valor;

	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private StatusTransferencia statusTransferencia;

	@Column(name = "MSG_STATUS")
	private String mensagemStatus;

	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private ContaCorrente conta;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIPO_OPERACAO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private TipoOperacao tipoOperacao;

	@Column(name = "CPF_OPERACAO")
	private String cpfOperacao;
	
	@Column(name = "NOME_CLIENTE_OPERACAO")
	private String nomeClienteOperacao;
	
	@Transient
	private String dadosContaOperacao;

	@Column(name = "AGENCIA_NUMERO")
	private String agenciaNumero;
	
	@Column(name = "AGENCIA_DIGITO")
	private String agenciaDigito;
	
	@Column(name = "CONTA_NUMERO")
	private String contaNumero;
	
	@Column(name = "CONTA_DIGITO")
	private String contaDigito;
	
	@Transient
	private String valorFormatado;

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getValorFormatado() {
		if (this.getValor() != null) {
			BigDecimal valor = new BigDecimal(this.getValor().doubleValue());
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			valorFormatado = nf.format(valor);
		}
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	public ContaCorrente getConta() {
		return conta;
	}

	public void setConta(ContaCorrente conta) {
		this.conta = conta;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getDataAlteracaoFormatada() {
		String dataAlteracaoFormatada = "";

		if (dataAlteracao != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

			dataAlteracaoFormatada = dataAlteracao.format(formatter);
		}

		return dataAlteracaoFormatada;
	}

	public String getCpfOperacao() {
		return cpfOperacao;
	}
	
	public void setCpfOperacao(String cpfOperacao) {
		this.cpfOperacao = cpfOperacao;
	}

	public String getNomeClienteOperacao() {
		return nomeClienteOperacao;
	}

	public void setNomeClienteOperacao(String nomeClienteOperacao) {
		this.nomeClienteOperacao = nomeClienteOperacao;
	}

	public String getDadosContaOperacao() {
		
		if(dadosContaOperacao == null || dadosContaOperacao.isEmpty()){
			StringBuilder sb =  new StringBuilder();
			sb.append("Agencia ").append(getAgenciaNumero()).append("-").append(getAgenciaDigito());
			sb.append(" / Conta ").append(getContaNumero()).append("-").append(getContaDigito());
			dadosContaOperacao = sb.toString();
		}
		
		return dadosContaOperacao;
	}

	public void setDadosContaOperacao(String dadosContaOperacao) {
		this.dadosContaOperacao = dadosContaOperacao;
	}

	public String getAgenciaNumero() {
		return agenciaNumero;
	}

	public void setAgenciaNumero(String agenciaNumero) {
		this.agenciaNumero = agenciaNumero;
	}

	public String getAgenciaDigito() {
		return agenciaDigito;
	}

	public void setAgenciaDigito(String agenciaDigito) {
		this.agenciaDigito = agenciaDigito;
	}

	public String getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(String contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getContaDigito() {
		return contaDigito;
	}

	public void setContaDigito(String contaDigito) {
		this.contaDigito = contaDigito;
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
