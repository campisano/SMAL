package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.smal.domain.Administrador;
import br.com.smal.domain.Tecnico;
import br.com.smal.infrastructure.AdministradorDao;

public class AdministradorRepositorio {

	@Autowired
	AdministradorDao administradorDao;
	
	public boolean incluir(Administrador entidade) {
		return administradorDao.incluir(entidade);
	}

	public Tecnico obter(long id) {
		return administradorDao.obter(id);
	}

	public boolean alterar(Administrador entidade) {
		return administradorDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return administradorDao.excluir(id);
	}

	public List<Administrador> obterTodos() {
		return administradorDao.obterTodos();
	}
	
	public boolean existeMatriculaSenha(String matricula , String senha){
	
		return administradorDao.existeMatriculaSenha(matricula , senha);
	}

	public Tecnico getAdministradorPorMatricula(String matricula){
		
		return administradorDao.getAdministradorPorMatricula(matricula);
	}
	
	
	
}

	

