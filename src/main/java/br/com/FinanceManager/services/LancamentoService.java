package br.com.FinanceManager.services;

import java.util.List;
import java.util.Optional;

import br.com.FinanceManager.model.Lancamento;
import br.com.FinanceManager.model.enums.StatusLancamento;

public interface LancamentoService {
	
	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar (Lancamento LancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lancamento);
	
	Optional<Lancamento> obterPorId(long id);
}
