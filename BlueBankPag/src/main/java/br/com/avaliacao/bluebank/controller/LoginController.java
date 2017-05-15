package br.com.avaliacao.bluebank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.Usuario;
import br.com.avaliacao.bluebank.repository.ClienteRepository;
import br.com.avaliacao.bluebank.service.ContaCorrenteService;
import br.com.avaliacao.bluebank.service.UsuarioService;
import br.com.avaliacao.bluebank.service.ViewHistoricoTransacaoService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
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
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = new Usuario();
		modelAndView.addObject("usuario", usuario);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario userExists = usuarioService.findByCpf(usuario.getCpf());
		if (userExists != null) {
			bindingResult
					.rejectValue("cpf", "error.user",
							"CPF já registrado!");
		}
	
		usuarioService.salvar(usuario);
		modelAndView.addObject("successMessage", "Usuário registrado com sucesso!!");
		modelAndView.addObject("user", new Usuario());
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
