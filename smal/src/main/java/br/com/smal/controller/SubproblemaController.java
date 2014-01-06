package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Maquina;
import br.com.smal.domain.Problema;
import br.com.smal.domain.Subproblema;
import br.com.smal.persistence.ProblemaRepositorio;
import br.com.smal.persistence.SubproblemaRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class SubproblemaController {
	@Autowired
	SubproblemaRepositorio subproblemaRepositorio;
	@Autowired
	ProblemaRepositorio problemaRepositorio;

	public OperationResult cadastrarSubproblema(String nome, long problemaId) {

		List<Subproblema> list = subproblemaRepositorio.obterTodos();

		for (Subproblema obj : list) {
			if (obj.getNome().equals(nome)) {
				return new OperationResult(false, "Erro: nome existente.");
			}
		}

		Problema problema = problemaRepositorio.obter(problemaId);

		// RN_XX: Problema deve existir.
		{
			if (problema == null) {
				return new OperationResultObject<List<Maquina>>(false,
						"Erro: problema não existe.", null);
			}
		}

		Subproblema subproblema = new Subproblema();
		subproblema.setNome(nome);
		subproblema.setProblema(problema);

		if (subproblemaRepositorio.incluir(subproblema)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResultObject<List<Subproblema>> listarSubproblemas() {
		List<Subproblema> objs = subproblemaRepositorio.obterTodos();

		if (objs != null) {
			return new OperationResultObject<List<Subproblema>>(true, "", objs);
		} else {
			return new OperationResultObject<List<Subproblema>>(false,
					"Erro interno.", null);
		}
	}

	public OperationResult alterarSubproblema(long id, String nome,
			long problemaId) {

		Subproblema subproblema = subproblemaRepositorio.obter(id);

		// RN_XX: Subproblema deve existir.
		{
			if (subproblema == null) {
				return new OperationResultObject<List<Maquina>>(false,
						"Erro: subproblema não existe.", null);
			}
		}

		List<Subproblema> list = subproblemaRepositorio.obterTodos();

		for (Subproblema obj : list) {
			if (obj.getNome().equals(nome) && obj.getId() != id) {
				return new OperationResult(false, "Erro: nome existente.");
			}
		}

		Problema problema = problemaRepositorio.obter(problemaId);

		// RN_XX: Problema deve existir.
		{
			if (problema == null) {
				return new OperationResultObject<List<Maquina>>(false,
						"Erro: problema não existe.", null);
			}
		}

		subproblema.setNome(nome);
		subproblema.setProblema(problema);

		if (subproblemaRepositorio.alterar(subproblema)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResult excluirSubproblema(long id) {
		if (subproblemaRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}
}
