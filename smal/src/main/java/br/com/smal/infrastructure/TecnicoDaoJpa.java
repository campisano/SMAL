package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Tecnico;
import br.com.smal.persistence.TecnicoDao;

@Component
public class TecnicoDaoJpa extends GenericDaoJpa<Tecnico> implements TecnicoDao{
	


	@Override
	public Tecnico obter(long id) {
		
	 return obterPorId(Tecnico.class, id);
	 
	}

	@Override
	public boolean excluir(long id) {
		
		return excluir(Tecnico.class, id);
	}

	@Override
	public List<Tecnico> obterTodos() {
		
		return obterTodos(Tecnico.class);
	}

	@Override
	public boolean existe(Tecnico entidade) {
		
		Tecnico tecnicoExistente = getTecnicoPorMatricula(entidade.getMatricula());
		
		if(tecnicoExistente!=null){
			
		  return true;	
		
		}else{
		
			return false;
		}
		
	}

	public Tecnico getTecnicoPorMatricula(String matricula) {
		String consulta = "SELECT a from tecnico a WHERE a.matricula = ?";
		Object array[] = { matricula };
		return super.obterEntidade(consulta, array);
	}

	
}
