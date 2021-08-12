package br.com.FinanceManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.FinanceManager.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
}
