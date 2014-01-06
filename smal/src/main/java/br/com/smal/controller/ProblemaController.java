package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;
import br.com.smal.persistence.ProblemaRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class ProblemaController {
	@Autowired
	ProblemaRepositorio problemaRepositorio;

	public OperationResult cadastrarProblema(String nome) {

		List<Problema> list = problemaRepositorio.obterTodos();

		for (Problema obj : list) {
			if (obj.getNome().equals(nome)) {
				return new OperationResult(false, "Erro: nome existente.");
			}
		}

		Problema problema = new Problema();
		problema.setNome(nome);

		if (problemaRepositorio.incluir(problema)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResultObject<List<Problema>> listarProblemas() {
		List<Problema> objs = problemaRepositorio.obterTodos();

		if (objs != null) {
			return new OperationResultObject<List<Problema>>(true, "", objs);
		} else {
			return new OperationResultObject<List<Problema>>(false,
					"Erro interno.", null);
		}
	}

	public OperationResult alterarProblema(long id, String nome) {

		Problema problema = problemaRepositorio.obter(id);

		if (problema == null) {
			return new OperationResult(false, "Erro: problema não existe.");
		}

		List<Problema> list = problemaRepositorio.obterTodos();

		for (Problema obj : list) {
			if (obj.getNome().equals(nome) && obj.getId() != id) {
				return new OperationResult(false, "Erro: nome existente.");
			}
		}

		problema.setNome(nome);

		if (problemaRepositorio.alterar(problema)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResult excluirProblema(long id) {
		if (problemaRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}
}
