package br.com.smal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Laboratorio;
import br.com.smal.domain.Maquina;
import br.com.smal.domain.Posicao;
import br.com.smal.persistence.LaboratorioRepositorio;
import br.com.smal.persistence.MaquinaRepositorio;
import br.com.smal.persistence.PosicaoRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class MaquinaController {
	@Autowired
	MaquinaRepositorio maquinaRepositorio;
	@Autowired
	LaboratorioRepositorio laboratorioRepositorio;
	@Autowired
	PosicaoRepositorio posicaoRepositorio;

	public OperationResultObject<List<Maquina>> listarMaquinas(
			long laboratorioId) {

		Laboratorio lab = laboratorioRepositorio.obter(laboratorioId);

		// RN_10: Laboratório deve existir.
		{
			if (lab == null) {
				return new OperationResultObject<List<Maquina>>(false,
						"Erro: laboratório não existe.", null);
			}
		}

		List<Maquina> maquinas_todas = maquinaRepositorio.obterTodos();

		if (maquinas_todas == null) {
			return new OperationResultObject<List<Maquina>>(false,
					"Erro: erro interno na listagem das máquinas.", null);
		} else {

			List<Maquina> maquinas = new ArrayList<Maquina>();

			for (Maquina maquina : maquinas_todas) {
				if (maquina.getPosicao().getLaboratorio().getId() == laboratorioId) {
					maquinas.add(maquina);
				}
			}

			return new OperationResultObject<List<Maquina>>(true, "", maquinas);
		}
	}

	public OperationResult cadastrarMaquina(String patrimonio,
			long laboratorioId, int fila, int coluna) {

		Laboratorio lab = laboratorioRepositorio.obter(laboratorioId);

		OperationResult check = checkRules(patrimonio, lab, fila, coluna);

		if (!check.isSuccess()) {
			return check;
		}

		// RN_09: Não pode existir mais que uma máquina com o mesmo patrimonio.
		{
			List<Maquina> maquinas = maquinaRepositorio.obterTodos();

			for (Maquina maq : maquinas) {
				if (maq.getPatrimonio().equals(patrimonio)) {
					return new OperationResult(false,
							"Erro: já existe uma máquina com o mesmo patrimonio.");
				}
			}
		}

		Posicao posicao = null;

		// RN_11: Uma posição de um laboratório só pode ter uma máquina.
		{
			for (Posicao pos : lab.getPosicoes()) {
				if (pos.getFila() == fila && pos.getColuna() == coluna) {
					if (pos.getMaquina() != null) {
						return new OperationResult(false,
								"Erro: posição já ocupada.");
					}

					posicao = pos;
				}
			}
		}

		if (posicao == null) {
			posicao = new Posicao(lab, fila, coluna);

			if (!posicaoRepositorio.incluir(posicao)) {
				return new OperationResult(false,
						"Erro: erro interno na inserção da posição.");
			}
		}

		Maquina maquina = new Maquina();
		maquina.setPatrimonio(patrimonio);
		maquina.setPosicao(posicao);

		if (!maquinaRepositorio.incluir(maquina)) {
			return new OperationResult(false,
					"Erro: erro interno na inserção da máquina.");
		} else {
			return new OperationResult(true, "");
		}
	}

	public OperationResult alterarMaquina(long id, String patrimonio,
			long laboratorioId, int fila, int coluna) {

		Laboratorio lab = laboratorioRepositorio.obter(laboratorioId);

		OperationResult check = checkRules(patrimonio, lab, fila, coluna);

		if (!check.isSuccess()) {
			return check;
		}

		Maquina maquina = maquinaRepositorio.obter(id);

		// RN_09: Não pode existir mais que uma máquina com o mesmo patrimonio.
		{
			List<Maquina> maquinas = maquinaRepositorio.obterTodos();

			for (Maquina maq : maquinas) {
				if (maq.getPatrimonio().equals(patrimonio) && maq != maquina) {
					return new OperationResult(false,
							"Erro: já existe uma máquina com o mesmo patrimonio.");
				}
			}
		}

		Posicao posicao = null;

		// RN_11: Uma posição de um laboratório só pode ter uma máquina.
		{
			for (Posicao pos : lab.getPosicoes()) {
				if (pos.getFila() == fila && pos.getColuna() == coluna) {
					if (pos.getMaquina() != null && pos.getMaquina() != maquina) {
						return new OperationResult(false,
								"Erro: posição já ocupada.");
					}

					posicao = pos;
				}
			}
		}

		if (posicao == null) {
			posicao = new Posicao(lab, fila, coluna);

			if (!posicaoRepositorio.incluir(posicao)) {
				return new OperationResult(false,
						"Erro: erro interno na inserção da posição.");
			}
		}

		maquina.setPatrimonio(patrimonio);
		maquina.setPosicao(posicao);

		if (!maquinaRepositorio.alterar(maquina)) {
			return new OperationResult(false,
					"Erro: erro interno na alteração da máquina.");
		} else {
			return new OperationResult(true, "");
		}
	}

	public OperationResult excluirMaquina(long id) {
		if (maquinaRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false,
					"Erro interno na exclusão da máquina.");
		}
	}

	private OperationResult checkRules(String patrimonio,
			Laboratorio laboratorio, int fila, int coluna) {
		// RN_01: Máximo 6 filas no laboratório.
		{
			if (fila > 6) {
				return new OperationResult(false,
						"Erro: Máximo 6 filas no laboratório.");
			}
		}

		// RN_08: Patrimonio é um texto não pode ser vazío.
		{
			if (patrimonio == null || patrimonio.length() == 0) {
				return new OperationResult(false,
						"Erro: patrimonio não pode ser vazío.");
			}
		}

		// RN_10: Laboratório deve existir.
		{
			if (laboratorio == null) {
				return new OperationResult(false,
						"Erro: laboratório não existe.");
			}
		}

		return new OperationResult(true, "");
	}
}
