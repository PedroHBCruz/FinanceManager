package br.com.FinanceManager.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.FinanceManager.api.dto.UsuarioDTO;
import br.com.FinanceManager.exceptions.ErroAutenticacao;
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
		Optional<Usuario> usuario = repository.findByEmail(email);
		
//		if(!usuario.isPresent() ||!usuario.get().getSenha().equals(senha) ) {
//			throw new ErroAutenticacao("Email ou senha incorretos.");
//		}
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
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
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getSenha());
	}
	
	public Usuario buscaPorId(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new RegraNegocioException(
				" Objeto não encontrado ! id:"+ id +"tipo:"+ Usuario.class.getName()));
	}

}
