package br.com.fiap.rh.service.reajuste;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.rh.model.Funcionario;
import br.com.fiap.rh.repository.FuncionarioRepository;

@Service
public class ReajusteService  implements IReajusteService{
	
	private List<ValidacaoReajuste> validacoes;
	
	private FuncionarioRepository funcionarioRepository;

	public ReajusteService(List<ValidacaoReajuste> validacoes,FuncionarioRepository funcionarioRepository) {//injecao de dependencia
		this.validacoes = validacoes;
		this.funcionarioRepository = funcionarioRepository;
	}
	
	//embora reajuste e salário estejam relacionados com o funcionário, aqui eu tenho algo que já está além disso que é o cálculo do reajuste, a validação de reajuste de salário
	//Pode ser que amanhã mude esse percentual, pode ser que tenham novas regras em razao disto esse trecho de código estar isolado na classe service.
    @Override
    public Funcionario reajustarSalarioDoFuncionario(Funcionario funcionario, BigDecimal aumento) {
		
		// abstração das validações aplicando o principio de  open closed e inversao de dependencia
		this.validacoes.forEach(v -> v.validar(funcionario, aumento));
		
		// garantindo a coesao e a Single Responsibility Principle
		BigDecimal salarioReajustado = funcionario.getSalario().add(aumento);
		funcionario.atualizarSalario(salarioReajustado);
		
		Funcionario funcionarioResponse = funcionarioRepository.save(funcionario);
		return funcionarioResponse;
	}
	
}
