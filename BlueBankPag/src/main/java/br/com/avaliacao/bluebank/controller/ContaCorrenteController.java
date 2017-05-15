package br.com.avaliacao.bluebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.Transferencia;
import br.com.avaliacao.bluebank.model.Usuario;
import br.com.avaliacao.bluebank.service.ClienteService;
import br.com.avaliacao.bluebank.service.ContaCorrenteService;
import br.com.avaliacao.bluebank.service.TransferenciaService;
import br.com.avaliacao.bluebank.service.UsuarioService;
import br.com.avaliacao.bluebank.service.ViewHistoricoTransacaoService;

@Controller
public class ContaCorrenteController {
	
	private static final String OK = "ok";

	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@Autowired
	private ViewHistoricoTransacaoService viewHistoricoTransacaoService;
	
	@PostMapping("/realizarTransferencia")
    public ModelAndView add(@ModelAttribute ContaCorrente contaOrigem) {
        
		contaOrigem = contaCorrenteService.findById(contaOrigem.getId());
		
		ModelAndView modelAndView = criarRedirecionamentoTransferencia(contaOrigem);
		
        return modelAndView;
	}
	
	
	private ModelAndView criarRedirecionamentoTransferencia(ContaCorrente contaOrigem) {
		StringBuilder dadosContaOrigem = new StringBuilder();
		dadosContaOrigem.append("\nCliente: ").append(contaOrigem.getCliente().getNome());
		dadosContaOrigem.append("\nAg. ").append(contaOrigem.getAgencia().getNumero()).append("-").append(contaOrigem.getAgencia().getDigito());
		dadosContaOrigem.append("\nConta ").append(contaOrigem.getNumero()).append("-").append(contaOrigem.getDigito());
		
		Transferencia transferencia =  new Transferencia();
		transferencia.setContaOrigemId(contaOrigem.getId());
		
        ModelAndView modelAndView = new ModelAndView("/admin/postRealizarTransferencia");
        modelAndView.addObject("transferencia", new Transferencia());
        modelAndView.addObject("contaOrigem", contaOrigem);
        modelAndView.addObject("dadosContaOrigem", dadosContaOrigem);
        
		return modelAndView;
	}


	@PostMapping("/confirmarTransferencia")
    public ModelAndView confirmarTransferencia(@ModelAttribute Transferencia transferencia) {
		Long contaOrigemId = transferencia.getContaOrigem().getId();
		ModelAndView modelAndView = new ModelAndView();
		
		String resultado = transferenciaService.transferir(transferencia);
		
		ContaCorrente contaOrigem = contaCorrenteService.findById(contaOrigemId);

		if(resultado.startsWith(OK)){
			modelAndView.setViewName("/admin/home");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = usuarioService.findByCpf(auth.getName());
			Cliente cliente =  clienteService.findByCpf(usuario.getCpf());
			
			modelAndView.addObject("contaOrigem", contaOrigem);
			modelAndView.addObject("userName", "Bem vindo, " + cliente.getNome());
			modelAndView.addObject("adminMessage","Bem vindo a área segura do Blue Bank");
			modelAndView.addObject("transacoes", viewHistoricoTransacaoService.findByConta(contaOrigem));
			modelAndView.addObject("contaDestino", new ContaCorrente());
			modelAndView.addObject("msgSucessoTransferencia", "Transferência efetuada!");
			
		}else{
			
			modelAndView = criarRedirecionamentoTransferencia(contaOrigem);
			modelAndView.addObject("msgStatusTransferencia", resultado);
		}
        
        return modelAndView;
	}
}
