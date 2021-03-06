package br.com.FinanceManager.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.FinanceManager.api.dto.UsuarioDTO;
import br.com.FinanceManager.exceptions.ErroAutenticacao;
import br.com.FinanceManager.model.Usuario;
import br.com.FinanceManager.services.LancamentoService;
import br.com.FinanceManager.services.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

	@Autowired
	private final UsuarioService service;
	@Autowired
	private final LancamentoService lancamentoService;
	
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		}catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO dto){
		
		Usuario obj = service.fromDTO(dto);
		obj = service.salvarUsuario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		//Da forma abaixo ele retorna os valores criados na resposta.
//		Usuario obj = service.fromDTO(dto);
//		Usuario usuarioSalvo = service.salvarUsuario(obj);
//		return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
	}
	@GetMapping("/{id}/saldo")
	public ResponseEntity<?> obterSaldo(@PathVariable Long id) {
		Optional<Usuario> usuario = service.obterPorId(id);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
	}
}

	
	
	

