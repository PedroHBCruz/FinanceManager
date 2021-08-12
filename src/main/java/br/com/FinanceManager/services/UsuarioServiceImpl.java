package br.com.FinanceManager.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.FinanceManager.exceptions.RegraNegocioException;
import br.com.FinanceManager.model.Usuario;
import br.com.FinanceManager.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	
	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
	}

}
