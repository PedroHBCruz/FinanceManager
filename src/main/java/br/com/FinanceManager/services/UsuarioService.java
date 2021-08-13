package br.com.FinanceManager.services;

import java.util.Optional;

import br.com.FinanceManager.api.dto.UsuarioDTO;
import br.com.FinanceManager.model.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Usuario fromDTO(UsuarioDTO objDto);
	
	Optional<Usuario> obterPorId(Long id);
	
}
