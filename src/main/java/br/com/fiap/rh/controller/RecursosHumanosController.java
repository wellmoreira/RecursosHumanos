package br.com.fiap.rh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.fiap.rh.model.Funcionario;
import br.com.fiap.rh.repository.FuncionarioRepository;
import br.com.fiap.rh.service.promocao.PromocaoService;
import br.com.fiap.rh.service.reajuste.ReajusteService;
import br.com.fiap.rh.service.reajuste.ValidacaoPercentualReajuste;
import br.com.fiap.rh.service.reajuste.ValidacaoPeriodicidadeEntreReajustes;
import br.com.fiap.rh.service.reajuste.ValidacaoReajuste;

@RestController
public class RecursosHumanosController {

	/*
	 * princípio da responsabilidade única, o princípio aberto e fechado e o
	 * princípio da substituição de Liskov e o princípio da segregação de interface
	 * e o princípio da inversão de dependências. Então cada um desses princípios
	 * formam o SOLID que são princípios focados em boas práticas de programação e
	 * de orientação a objetos.
	 * 
	 * 
	 * SOLID visa escrever um código que é fácil de manter e principalmente fácil de
	 * estender, de adicionar novas características, novos comportamentos, novas
	 * funcionalidades com um menor impacto possível.
	 * 
	 * Então esses princípios visam justamente simplificar esse processo de
	 * manutenção no seu código.
	 */

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	private PromocaoService promocaoService;
	private ReajusteService reajusteService;
	private List<ValidacaoReajuste> list = new ArrayList<ValidacaoReajuste>();
	private ValidacaoPercentualReajuste validacaoPercentualReajuste = new ValidacaoPercentualReajuste();
	private ValidacaoPeriodicidadeEntreReajustes validacaoPeriodicidadeEntreReajustes = new ValidacaoPeriodicidadeEntreReajustes();
	String retorno = "";

	@GetMapping("/hello-word")
	@ResponseBody
	public String hello() {
		return "Hello";
	}

	@PostMapping("/reajusteSalarial")
	public ResponseEntity<String> reajusteSalarial(@RequestBody Funcionario func) throws JsonProcessingException {
		list.add(validacaoPercentualReajuste);
		list.add(validacaoPeriodicidadeEntreReajustes);

		reajusteService = new ReajusteService(list, funcionarioRepository);
		Funcionario funcResponse = reajusteService.reajustarSalarioDoFuncionario(func, BigDecimal.ONE);

		if (funcResponse != null) {
			retorno = retorno.concat("funcionario atualizado com sucesso");
		}
		return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	}

	@PostMapping("/promocao")
	public ResponseEntity<String> promocao(@RequestBody Funcionario func) throws JsonProcessingException {

		Funcionario funcResponse = promocaoService.promover(func, true);

		if (funcResponse != null) {
			retorno = retorno.concat("funcionario atualizado com sucesso");
		}
		return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	}

}
