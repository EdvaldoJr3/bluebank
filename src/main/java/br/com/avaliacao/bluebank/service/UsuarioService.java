package br.com.avaliacao.bluebank.service;

import java.util.Collection;

import br.com.avaliacao.bluebank.model.Usuario;

public interface UsuarioService {
	
	public Collection<Usuario> findAll();
	public Usuario findByCpf(String cpf);
	public void salvar(Usuario usuario);
}
