package br.com.smal.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.smal.domain.Administrador;
import br.com.smal.domain.Tecnico;

public class AdministradorDaoJpa extends GenericDaoJpa<Administrador> implements AdministradorDao {

	EntityManager em = new JpaUtil().getEntityManager();


	@Override
	public Tecnico obter(long id) {
		
	 return obterPorId(Administrador.class, id);
	 
	}

	@Override
	public boolean excluir(long id) {
		
		return excluir(Administrador.class, id);
	}

	@Override
	public List<Administrador> obterTodos() {
		
		return obterTodos(Administrador.class);
	}

	@Override
	public boolean existe(Administrador entidade) {
		
		Administrador administradorExistente = getAdministradorPorMatricula(entidade.getMatricula());
		
		if(administradorExistente!=null){
			
		  return true;	
		
		}else{
		
			return false;
		}
		
	}
	
	public boolean existeMatriculaSenha(String matricula , String senha){

		StringBuilder cs = new StringBuilder();
		cs.append("SELECT a FROM tecnico a WHERE a.matricula = ?");
		cs.append(" AND a.senha = ?");
	
		Query query = em.createQuery(cs.toString());
		query.setParameter(1, matricula);
		query.setParameter(2, senha);

		if(query.getSingleResult()!=null){
			return true;
		
		}else{
			return false;				
		}
	}


	public Administrador getAdministradorPorMatricula(String matricula) {
		String consulta = "SELECT a from tecnico a WHERE a.matricula = ?";
		Object array[] = { matricula };
		return super.obterEntidade(consulta, array);
	}


}
