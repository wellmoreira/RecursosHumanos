package br.com.fiap.rh.service.reajuste;

import java.math.BigDecimal;

import br.com.fiap.rh.model.Funcionario;

public interface IReajusteService {
	 Funcionario reajustarSalarioDoFuncionario(Funcionario funcionario, BigDecimal aumento);
}
