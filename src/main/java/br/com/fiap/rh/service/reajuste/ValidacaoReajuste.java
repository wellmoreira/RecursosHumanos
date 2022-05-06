package br.com.fiap.rh.service.reajuste;

import java.math.BigDecimal;

import br.com.fiap.rh.model.Funcionario;

public interface ValidacaoReajuste {
	//  classe   extensivel que ajuda a implementar o conceito de open closed do solid atraves de interface e polimorfismo
	void validar(Funcionario funcionario, BigDecimal aumento);

}
