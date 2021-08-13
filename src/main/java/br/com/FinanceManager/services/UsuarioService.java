package br.com.FinanceManager.services;

import br.com.FinanceManager.api.dto.UsuarioDTO;
import br.com.FinanceManager.model.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Usuario fromDTO(UsuarioDTO objDto);
	
	Usuario buscaPorId(Long id);
	
}
