package br.com.FinanceManager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import br.com.FinanceManager.model.enums.StatusLancamento;
import br.com.FinanceManager.model.enums.TipoLancamento;
import lombok.Data;

@Entity
@Table(name = "lancamento", schema = "financas")
@Data
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private Integer ano;
	
	private Integer mes;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	private BigDecimal valor;
	
	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipo;
	
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento status;

	public Lancamento() {

	}
	
	public Lancamento(Long id, String descricao, Integer ano, Integer mes, Usuario usuario, BigDecimal valor,
			LocalDate dataCadastro, TipoLancamento tipo, StatusLancamento status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ano = ano;
		this.mes = mes;
		this.usuario = usuario;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
		this.tipo = tipo;
		this.status = status;
	}
	
}
