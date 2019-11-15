package br.com.avaliacao.bluebank.controller;

import javax.validation.Valid;

import br.com.avaliacao.bluebank.dto.ClienteDTO;
import br.com.avaliacao.bluebank.enums.Status;
import br.com.avaliacao.bluebank.model.*;
import br.com.avaliacao.bluebank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.avaliacao.bluebank.repository.ClienteRepository;

import java.time.LocalDateTime;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@Autowired
	private ContaCorrenteSaldoService contaCorrenteSaldoService;

	@Autowired
	private AgenciaService agenciaService;
	
	@Autowired
	private ViewHistoricoTransacaoService viewHistoricoTransacaoService;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Agencia agencia;
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("clienteDTO", new ClienteDTO());
		modelAndView.addObject("agencias", agenciaService.findAll());

		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid ClienteDTO dto, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = new Usuario(dto);
		Cliente cliente = new Cliente(dto);
        ContaCorrente contaCorrente =  new ContaCorrente(dto);
		ContaCorrenteSaldo saldo =  new ContaCorrenteSaldo();

		Usuario userExists = usuarioService.findByCpf(cliente.getCpf());
		if (userExists != null) {
			bindingResult
					.rejectValue("cpf", "error.user",
							"CPF já registrado!");
		}

		Cliente clienteExists = clienteRepository.findByCpf(usuario.getCpf());

		if(clienteExists == null){
			clienteRepository.save(cliente);
		}else{
			bindingResult
					.rejectValue("cpf", "error.user",
							"Cliente já registrado!");
		}

		contaCorrente.setClienteId(cliente.getId());
		usuario.setClienteId(cliente.getId());

		try {
			contaCorrenteService.gerarNumeroConta(contaCorrente, dto.getAgenciaId());
		} catch (Exception e) {
			bindingResult.rejectValue("agenciaId", "error.user",	e.getMessage());
		}
		contaCorrenteService.salvar(contaCorrente);

		saldo.setContaCorrenteId(contaCorrente.getId());

		contaCorrenteSaldoService.salvar(saldo);

		usuarioService.salvar(usuario);

		dto.setNumeroConta(contaCorrente.getNumero());
		dto.setDigitoConta(contaCorrente.getDigito());

		modelAndView.addObject("successMessage", "Usuário registrado com sucesso!!");
		modelAndView.addObject("clienteDTO", dto);
		modelAndView.setViewName("registration");
			
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.findByCpf(auth.getName());
		Cliente cliente =  clienteRepository.findByCpf(usuario.getCpf());
		ContaCorrente contaCorrente = contaCorrenteService.findByCliente(cliente);
		
		modelAndView.addObject("userName", "Bem vindo, " + cliente.getNome());
		modelAndView.addObject("adminMessage","Bem vindo a área segura do Blue Bank");
		modelAndView.addObject("transacoes", viewHistoricoTransacaoService.findByConta(contaCorrente));
		modelAndView.addObject("contaOrigem", contaCorrente);
		modelAndView.addObject("contaDestino", new ContaCorrente());
		modelAndView.setViewName("admin/home");
		
		return modelAndView;
	}
	

}
