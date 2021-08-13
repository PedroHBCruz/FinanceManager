package br.com.FinanceManager.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.FinanceManager.api.dto.LancamentoDTO;
import br.com.FinanceManager.exceptions.RegraNegocioException;
import br.com.FinanceManager.model.Lancamento;
import br.com.FinanceManager.model.Usuario;
import br.com.FinanceManager.model.enums.StatusLancamento;
import br.com.FinanceManager.model.enums.TipoLancamento;
import br.com.FinanceManager.services.LancamentoService;
import br.com.FinanceManager.services.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody LancamentoDTO dto) {
		
		try {
			Lancamento entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
			
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody LancamentoDTO dto ) {
		return service.obterPorId(id).map( entity -> {
			
			try {
				Lancamento lancamento = converter(dto);
				lancamento.setId(entity.getId());
				service.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
				
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () ->
			new ResponseEntity("Lancamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = 
				usuarioService.obterPorId(dto.getUsuario())
				.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado.") );
			
			lancamento.setUsuario(usuario);
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		
		return lancamento;
	}

}
