package br.com.avaliacao.bluebank.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import br.com.avaliacao.bluebank.model.UsuarioRole;
import br.com.avaliacao.bluebank.repository.UsuarioRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.avaliacao.bluebank.enums.Status;
import br.com.avaliacao.bluebank.model.Role;
import br.com.avaliacao.bluebank.model.Usuario;
import br.com.avaliacao.bluebank.repository.RoleRepository;
import br.com.avaliacao.bluebank.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

	@Override
	public void salvar(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario.setStatus(Status.ATIVO);
		usuario.setDataAlteracao(LocalDateTime.now());

		//usuario.setRoles(new HashSet<Role>(Arrays.asList(role)));
		usuarioRepository.save(usuario);

		Role role = roleRepository.findByRole("ADMIN");
		UsuarioRole usuarioRole = new UsuarioRole(usuario, role);
		usuarioRoleRepository.save(usuarioRole);

	}

	@Override
	public Collection<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

}
