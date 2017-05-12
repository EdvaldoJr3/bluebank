package br.com.avaliacao.bluebank.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.avaliacao.bluebank.model.Usuario;
import br.com.avaliacao.bluebank.repository.UsuarioRepository;

@Component
class UsuarioCommandLineRunner implements CommandLineRunner {
	
	@Override
	public void run(String... arg0) throws Exception {
		for (Usuario usuario : usuarioRepository.findAll()) {
			System.out.println("ID: " + usuario.getId() + " CPF: " + usuario.getCpf());
		}
	}

	@Autowired
	UsuarioRepository usuarioRepository;
}
