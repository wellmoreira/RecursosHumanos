package br.com.fiap.rh.service.promocao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.rh.exception.ValidacaoException;
import br.com.fiap.rh.model.Cargo;
import br.com.fiap.rh.model.Funcionario;
import br.com.fiap.rh.repository.FuncionarioRepository;

@Service
public class PromocaoService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario promover(Funcionario funcionario, boolean metaBatida) {// se usassemos heranca iria ser possivel passar um terceiro como parametro e isso iria ferir o principio de liskov
		Cargo cargoAtual = funcionario.getDadosPessoais().getCargo();
		if (Cargo.GERENTE == cargoAtual) {
			throw new ValidacaoException("Gerentes nao podem ser promovidos!");
		}
		// garantindo a coesao e a Single Responsibility Principle a classe service nao precisa conhecer o cargo apenas precisa saber a regra de negocio por tras da promocao
		if (metaBatida) {
			Cargo novoCargo = cargoAtual.getProximoCargo();
			funcionario.promover(novoCargo);
			
			return funcionarioRepository.save(funcionario);
		} else {
			throw new ValidacaoException("Funcionario nao bateu a meta!");
		}
	}

}
