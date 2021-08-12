package br.com.FinanceManager.services;

import br.com.FinanceManager.model.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
}
