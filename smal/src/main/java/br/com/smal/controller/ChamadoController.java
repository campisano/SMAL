package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Chamado;
import br.com.smal.domain.Maquina;
import br.com.smal.domain.Status;
import br.com.smal.domain.Subproblema;
import br.com.smal.domain.Tecnico;
import br.com.smal.domain.Usuario;
import br.com.smal.persistence.ChamadoRepositorio;
import br.com.smal.persistence.MaquinaRepositorio;
import br.com.smal.persistence.SubproblemaRepositorio;
import br.com.smal.persistence.TecnicoRepositorio;
import br.com.smal.persistence.UsuarioRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class ChamadoController {
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Autowired
	TecnicoRepositorio tecnicoRepositorio;
	@Autowired
	MaquinaRepositorio maquinaRepositorio;
	@Autowired
	SubproblemaRepositorio subproblemaRepositorio;
	@Autowired
	ChamadoRepositorio chamadoRepositorio;

	public OperationResultObject<List<Chamado>> listarChamados() {
		List<Chamado> chamados = chamadoRepositorio.obterTodos();

		if (chamados == null) {
			return new OperationResultObject<List<Chamado>>(false,
					"Erro: erro interno na listagem dos chamados.", null);
		} else {
			return new OperationResultObject<List<Chamado>>(true, "", chamados);
		}
	}

	public OperationResult abrirChamado(String matricula, long maquinaId,
			long subproblemaId, String descricao) {

		Usuario usuario = null;

		// RN_XX: se o usuário da matrícula não existir, o cria
		{
			List<Usuario> usuarios = usuarioRepositorio.obterTodos();
			{
				for (Usuario usuario_existente : usuarios) {
					if (usuario_existente.getMatricula().equals(matricula)) {
						usuario = usuario_existente;
						break;
					}
				}
			}

			if (usuario == null) {
				usuario = new Usuario();
				usuario.setMatricula(matricula);
				if (!usuarioRepositorio.incluir(usuario)) {
					return new OperationResult(false,
							"Erro interno na inclusão do usuário.");
				}
			}
		}

		Maquina maquina = maquinaRepositorio.obter(maquinaId);
		// RN_XX: Máquina deve existir.
		{
			if (maquina == null) {
				return new OperationResult(false, "Erro: máquina não existe.");
			}
		}

		Subproblema subproblema = subproblemaRepositorio.obter(subproblemaId);
		// RN_XX: Subproblema deve existir.
		{
			if (subproblema == null) {
				return new OperationResult(false,
						"Erro: subproblema não existe.");
			}
		}

		Chamado chamado = new Chamado();
		chamado.setAbridor(usuario);
		chamado.setSubproblema(subproblema);
		chamado.setDescricao(descricao);
		chamado.setMaquina(maquina);
		chamado.setStatus(Status.ABERTO);

		if (chamadoRepositorio.incluir(chamado)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false,
					"Erro interno na inclusão do chamado.");
		}
	}

	public OperationResult designarChamado(long id, long tecnicoId) {
		Chamado chamado = chamadoRepositorio.obter(id);

		// RN_XX: chamado deve existir
		if (chamado == null) {
			return new OperationResult(false, "Erro: chamado não existe.");
		}

		Tecnico tecnico = tecnicoRepositorio.obter(tecnicoId);

		// RN_XX: técnico deve existir
		if (tecnico == null) {
			return new OperationResult(false, "Erro: tecnico não existe.");
		}

		chamado.setStatus(Status.EM_ANDAMENTO);
		chamado.setAtendente(tecnico);

		if (!chamadoRepositorio.alterar(chamado)) {
			return new OperationResult(false,
					"Erro interno na alteração do chamado.");
		}

		return new OperationResult(true, "");
	}

	public OperationResult fecharChamado(long id, boolean exito) {
		Chamado chamado = chamadoRepositorio.obter(id);

		// RN_XX: chamado deve existir
		if (chamado == null) {
			return new OperationResult(false, "Erro: chamado não existe.");
		}

		// RN_XX: chamado deve ser em andamento
		if (!(chamado.getStatus() == Status.EM_ANDAMENTO)) {
			return new OperationResult(false,
					"Erro: chamado deve ser em andamento.");
		}

		if (exito) {
			chamado.setStatus(Status.RESOLVIDO);
		} else {
			chamado.setStatus(Status.NAO_RESOLVIDO);
		}

		if (!chamadoRepositorio.alterar(chamado)) {
			return new OperationResult(false,
					"Erro interno na alteração do chamado.");
		}

		return new OperationResult(true, "");
	}
}
