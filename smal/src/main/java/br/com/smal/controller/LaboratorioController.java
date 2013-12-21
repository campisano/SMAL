package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Laboratorio;
import br.com.smal.persistence.LaboratorioRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class LaboratorioController {
	@Autowired
	LaboratorioRepositorio laboratorioRepositorio;

	public OperationResult incluir(Laboratorio laboratorio) {

		List<Laboratorio> list = laboratorioRepositorio.obterTodos();

		for (Laboratorio lab : list) {
			if (lab.getNome().equals(laboratorio.getNome())) {
				return new OperationResult(false, "Nome existente.");
			}
		}

		if (laboratorioRepositorio.incluir(laboratorio)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResultObject<Laboratorio> obter(long id) {
		Laboratorio lab = laboratorioRepositorio.obter(id);

		if (lab != null) {
			return new OperationResultObject<Laboratorio>(true, "", lab);
		} else {
			return new OperationResultObject<Laboratorio>(false,
					"Erro interno.", null);
		}
	}

	public OperationResultObject<List<Laboratorio>> obterTodos() {
		List<Laboratorio> labs = laboratorioRepositorio.obterTodos();

		if (labs != null) {
			return new OperationResultObject<List<Laboratorio>>(true, "", labs);
		} else {
			return new OperationResultObject<List<Laboratorio>>(false,
					"Erro interno.", null);
		}
	}

	public OperationResult alterar(long id, Laboratorio laboratorio) {

		List<Laboratorio> list = laboratorioRepositorio.obterTodos();

		for (Laboratorio lab : list) {
			if (lab.getNome().equals(laboratorio.getNome())) {
				return new OperationResult(false, "Nome existente.");
			}
		}

		Laboratorio laboratorio_salvo = laboratorioRepositorio.obter(id);

		if (laboratorio_salvo == null) {
			return new OperationResult(false, "Erro interno.");
		}

		laboratorio_salvo.setNome(laboratorio.getNome());

		if (laboratorioRepositorio.alterar(laboratorio_salvo)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResult excluir(long id) {
		if (laboratorioRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}
}
