package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Tecnico;

@Component
public class TecnicoRepositorio {
 
	@Autowired
	TecnicoDao tecnicoDao;
	
	public boolean incluir(Tecnico entidade) {
		return tecnicoDao.incluir(entidade);
	}

	public Tecnico obter(long id) {
		return tecnicoDao.obter(id);
	}

	public boolean alterar(Tecnico entidade) {
		return tecnicoDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return tecnicoDao.excluir(id);
	}

	public List<Tecnico> obterTodos() {
		return tecnicoDao.obterTodos();
	}
	
	public boolean existe(Tecnico entidade){
	
		return tecnicoDao.existe(entidade);
	}

	public Tecnico getTecnicoPorMatricula(String matricula){
		
		return tecnicoDao.getTecnicoPorMatricula(matricula);
	}
	
	
	
}
