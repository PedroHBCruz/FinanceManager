package br.com.FinanceManager.api.dto;

import br.com.FinanceManager.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome(); 
		email = obj.getEmail();
		senha = obj.getSenha();
	}

}
