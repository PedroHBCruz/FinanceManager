package br.com.FinanceManager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.FinanceManager.model.Lancamento;
import br.com.FinanceManager.model.enums.StatusLancamento;
import br.com.FinanceManager.repositories.LancamentoRepository;

@Service
public class LancamentoServiceImpl implements LancamentoService{

	@Autowired
	private LancamentoRepository repository;
	
	@Override
	public Lancamento salvar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lancamento atualizar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Lancamento lancamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lancamento> buscar(Lancamento LancamentoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		// TODO Auto-generated method stub
		
	}

}
