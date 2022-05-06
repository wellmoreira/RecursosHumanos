package br.com.fiap.rh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.rh.model.Cargo;
import br.com.fiap.rh.model.Funcionario;
import br.com.fiap.rh.service.promocao.PromocaoService;
import br.com.fiap.rh.service.reajuste.ReajusteService;
import br.com.fiap.rh.service.reajuste.ValidacaoPercentualReajuste;
import br.com.fiap.rh.service.reajuste.ValidacaoPeriodicidadeEntreReajustes;
import br.com.fiap.rh.service.reajuste.ValidacaoReajuste;

@RestController
public class RecursosHumanosController {

	private ReajusteService reajusteService;

	private List<ValidacaoReajuste> list = new ArrayList<ValidacaoReajuste>();

	private ValidacaoPercentualReajuste validacaoPercentualReajuste = new ValidacaoPercentualReajuste();

	private ValidacaoPeriodicidadeEntreReajustes validacaoPeriodicidadeEntreReajustes = new ValidacaoPeriodicidadeEntreReajustes();

	@Autowired
	private PromocaoService promocaoService;

	ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/hello-word")
	@ResponseBody
	public String hello() {
		return "Hello worldiiiiiiiiii";
	}

	@GetMapping("/reajusteSalarial")
	public ResponseEntity<Boolean> reajusteSalarial() throws JsonProcessingException {
		list.add(validacaoPercentualReajuste);
		list.add(validacaoPeriodicidadeEntreReajustes);

		reajusteService = new ReajusteService(list);
		boolean retorno = reajusteService.reajustarSalarioDoFuncionario(
				new Funcionario("Fulano", "Silva", Cargo.ANALISTA, BigDecimal.TEN), BigDecimal.ONE);

		return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	}

	@GetMapping("/promocao")
	public ResponseEntity<Boolean> promocao() throws JsonProcessingException {
		boolean retorno = promocaoService.promover(new Funcionario("Fulano", "Silva", Cargo.ANALISTA, BigDecimal.ONE),	true);

		return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	}

}
