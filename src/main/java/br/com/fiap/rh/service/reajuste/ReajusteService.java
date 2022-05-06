package br.com.fiap.rh.service.reajuste;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.rh.model.Funcionario;

@Service
public class ReajusteService {
	
	private List<ValidacaoReajuste> validacoes;

	public ReajusteService(List<ValidacaoReajuste> validacoes) {
		this.validacoes = validacoes;
	}
	
	//embora reajuste e salário estejam relacionados com o funcionário, aqui eu tenho algo que já está além disso que é o cálculo do reajuste, a validação de reajuste de salário
	//Pode ser que amanhã mude esse percentual, pode ser que tenham novas regras em razao disto esse trecho de código estar isolado na classe service.
	public boolean reajustarSalarioDoFuncionario(Funcionario funcionario, BigDecimal aumento) {
		
		// abstração das validações aplicando o principio de  open closed e inversao de dependencia
		this.validacoes.forEach(v -> v.validar(funcionario, aumento));
		
		// garantindo a coesao e a Single Responsibility Principle
		BigDecimal salarioReajustado = funcionario.getSalario().add(aumento);
		funcionario.atualizarSalario(salarioReajustado);
		return true;
	}
	
}
