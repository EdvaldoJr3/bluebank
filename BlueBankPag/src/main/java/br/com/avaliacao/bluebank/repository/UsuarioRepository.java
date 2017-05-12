package br.com.avaliacao.bluebank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Collection<Usuario> findByCpf(String cpf);
}