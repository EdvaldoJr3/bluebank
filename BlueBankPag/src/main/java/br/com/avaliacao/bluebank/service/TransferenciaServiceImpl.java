package br.com.avaliacao.bluebank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.bluebank.enums.StatusTransferencia;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ContaCorrenteSaldo;
import br.com.avaliacao.bluebank.model.TransacaoHistorico;
import br.com.avaliacao.bluebank.model.Transferencia;
import br.com.avaliacao.bluebank.model.TransferenciaTransacaoHistorico;
import br.com.avaliacao.bluebank.repository.ContaCorrenteSaldoRepository;
import br.com.avaliacao.bluebank.repository.TransacaoHistoricoRepository;
import br.com.avaliacao.bluebank.repository.TransferenciaRepository;
import br.com.avaliacao.bluebank.repository.TransferenciaTransacaoHistoricoRepository;

@Service("transferenciaService")
public class TransferenciaServiceImpl implements TransferenciaService {

	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private TransacaoHistoricoRepository transacaoHistoricoRepository;

	@Autowired
	private TransferenciaTransacaoHistoricoRepository transferenciaTransacaoHistoricoRepository;

	@Autowired
	private ContaCorrenteSaldoRepository contaCorrenteSaldoRepository;

	@PersistenceContext
	private EntityManager em;

	private static String MSG_CONTA_DESTINO_NAO_EXISTE = "A conta para qual deseja realizar a transferência não foi encontrada. Favor verificar os dados!";
	private static String MSG_SALDO_INSUFICIENTE = "Seu saldo é insuficiente!";
	private static String MSG_TRANSFERENCIA_MESMO_CLIENTE = "Não é possível realizar transferência para a mesma conta.";
	private static String MSG_ERRO_TRANSACAO = "Não foi possível realizar a transação no momento. Por favor, tente mais tarde.";

	private static String MSG_OK = "ok";
	private static Long TIPO_OPERACAO_CREDITO = 1L;
	private static Long TIPO_OPERACAO_DEBITO = 2L;

	@Override
	@Transactional()
	public String transferir(Transferencia transferencia) {

		try {

			ContaCorrente contaDestino = contaCorrenteService
					.obterContaPorAgenciaNumero(transferencia.getContaDestino());

			if (!contaDestino.exists()) {
				return MSG_CONTA_DESTINO_NAO_EXISTE;
			}

			ContaCorrente contaOrigem = contaCorrenteService.findById(transferencia.getContaOrigem().getId());
			
			if(contaOrigem.getId() == contaDestino.getId()){
				return MSG_TRANSFERENCIA_MESMO_CLIENTE;
			}
			
			ContaCorrenteSaldo saldoOrigem = contaOrigem.getSaldo();
			ContaCorrenteSaldo saldoDestino = contaDestino.getSaldo();

			LocalDateTime dataHoraTransacao = LocalDateTime.now();

			// Bloqueando escrita nos registros de saldo
			em.lock(saldoOrigem, LockModeType.PESSIMISTIC_WRITE);
			em.lock(saldoDestino, LockModeType.PESSIMISTIC_WRITE);

			BigDecimal novoSaldoOrigem = saldoOrigem.getValor().subtract(transferencia.getValor());

			if (novoSaldoOrigem.compareTo(new BigDecimal(0)) < 0) {
				// Liberando os registros de saldos para escrita
				em.lock(saldoOrigem, LockModeType.NONE);
				em.lock(saldoDestino, LockModeType.NONE);

				return MSG_SALDO_INSUFICIENTE;
			}

			BigDecimal novoSaldoDestino = saldoDestino.getValor().add(transferencia.getValor());

			//Preparando registro da transferência
			Transferencia novaTransferencia = new Transferencia();
			
			novaTransferencia.setContaOrigemId(contaOrigem.getId());
			novaTransferencia.setContaDestinoId(contaDestino.getId());
			novaTransferencia.setStatusTransferencia(StatusTransferencia.SUCESSO);
			novaTransferencia.setDataAlteracao(dataHoraTransacao);
			novaTransferencia.setValor(transferencia.getValor());

			transferenciaRepository.save(novaTransferencia);

			// Registrando débito do cliente origem
			TransacaoHistorico transacaoHistoricoDebito = new TransacaoHistorico(contaOrigem.getId(),
					contaDestino.getId(), dataHoraTransacao, TIPO_OPERACAO_DEBITO, novaTransferencia.getValor());

			transacaoHistoricoRepository.save(transacaoHistoricoDebito);

			// Registrando crédito do cliente destino
			TransacaoHistorico transacaoHistoricoCredito = new TransacaoHistorico(contaOrigem.getId(),
					contaDestino.getId(), dataHoraTransacao, TIPO_OPERACAO_CREDITO, novaTransferencia.getValor());

			transacaoHistoricoRepository.save(transacaoHistoricoCredito);

			// Vinculando os históricos a transação
			TransferenciaTransacaoHistorico transferenciaTransacaoHistoricoDebito = new TransferenciaTransacaoHistorico(
					novaTransferencia.getId(), transacaoHistoricoDebito.getId(), dataHoraTransacao);

			transferenciaTransacaoHistoricoRepository.save(transferenciaTransacaoHistoricoDebito);

			TransferenciaTransacaoHistorico transferenciaTransacaoHistoricoCredito = new TransferenciaTransacaoHistorico(
					novaTransferencia.getId(), transacaoHistoricoCredito.getId(), dataHoraTransacao);

			transferenciaTransacaoHistoricoRepository.save(transferenciaTransacaoHistoricoCredito);

			// Atualizando o saldo dos dois clientes
			saldoOrigem.setValor(novoSaldoOrigem);
			saldoOrigem.setDataAlteracao(dataHoraTransacao);

			contaCorrenteSaldoRepository.save(saldoOrigem);

			saldoDestino.setValor(novoSaldoDestino);
			saldoDestino.setDataAlteracao(dataHoraTransacao);

			contaCorrenteSaldoRepository.save(saldoDestino);

			// Liberando os registros de saldos para escrita
			em.lock(saldoOrigem, LockModeType.NONE);
			em.lock(saldoDestino, LockModeType.NONE);

			return MSG_OK + novaTransferencia.getId();

		} catch (Exception e) {

			return MSG_ERRO_TRANSACAO;
		}

	}

	@Override
	public Transferencia findById(Long transferenciaId) {
		return transferenciaRepository.findOne(transferenciaId);
	}

}
