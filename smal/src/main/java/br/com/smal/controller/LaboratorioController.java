package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.OperationResult;
import br.com.smal.OperationResultObject;
import br.com.smal.domain.Laboratorio;
import br.com.smal.persistence.LaboratorioRepositorio;

@Component
public class LaboratorioController {
	@Autowired
	LaboratorioRepositorio laboratorioRepositorio;

	private LaboratorioController() {
	}

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

	public OperationResultObject<Laboratorio> obter(Long id) {
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

	public OperationResult alterar(Laboratorio laboratorio_salvo) {
		if (laboratorioRepositorio.alterar(laboratorio_salvo)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResult excluir(Long id) {
		if (laboratorioRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}
}
