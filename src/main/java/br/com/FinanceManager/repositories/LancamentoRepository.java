package br.com.FinanceManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.FinanceManager.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
