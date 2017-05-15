package br.com.avaliacao.bluebank.run;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.avaliacao.bluebank.model.Agencia;
import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.Usuario;
import br.com.avaliacao.bluebank.repository.AgenciaRepository;
import br.com.avaliacao.bluebank.repository.ClienteRepository;
import br.com.avaliacao.bluebank.repository.ContaCorrenteRepository;
import br.com.avaliacao.bluebank.repository.UsuarioRepository;

@Component
public class TestCommandLineRunner implements CommandLineRunner {

	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	//@Autowired
	//private ContaCorrenteRepository contaCorrenteRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		Collection<Usuario> usuarios =  usuarioRepository.findAll();
		
		if(!usuarios.isEmpty()){
			System.out.println("\n >>>>>>>> USUARIOS <<<<<<<< ");
			for (Usuario usuario :usuarios) {
				System.out.println("ID: " + usuario.getId() + " CPF: " + usuario.getCpf());
			}
		}
		
		Collection<Agencia> agencias =  agenciaRepository.findAll();
		
		if(!usuarios.isEmpty()){
			System.out.println("\n >>>>>>>> AGENCIAS <<<<<<<< ");
			for (Agencia agencia :agencias) {
				System.out.println("ID: " + agencia.getId() + " NUMERO: " + agencia.getNumero());
			}
		}
		
		Collection<Cliente> clientes =  clienteRepository.findAll();
		
		if(!clientes.isEmpty()){
			System.out.println("\n >>>>>>>> CLIENTES <<<<<<<< ");
			for (Cliente cliente :clientes) {
				System.out.println("ID: " + cliente.getId() + " NOME: " + cliente.getCpf());
			}
		}
		
		Collection<ContaCorrente> contas = (Collection<ContaCorrente>) contaCorrenteRepository.findAll();
		
		if(!contas.isEmpty()){
			System.out.println("\n >>>>>>>> CONTAS <<<<<<<< ");
			
			for (ContaCorrente contaCorrente : contas) {
				System.out.println("ID: " + contaCorrente.getId() + " NUMERO: " + contaCorrente.getNumero());
			}
		}
		
	}

}
