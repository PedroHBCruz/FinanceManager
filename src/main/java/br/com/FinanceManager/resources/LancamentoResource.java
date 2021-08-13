package br.com.FinanceManager.resources;

import org.springframework.beans.factory.annotation.Autowired;
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
