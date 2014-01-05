package br.com.smal.presentation.chamado.request;

public class AbrirChamadoRequest {
	private String matricula;
	private long maquinaId;
	private long subproblemaId;
	private String descricao;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public long getMaquinaId() {
		return maquinaId;
	}

	public void setMaquinaId(long maquinaId) {
		this.maquinaId = maquinaId;
	}

	public long getSubproblemaId() {
		return subproblemaId;
	}

	public void setSubproblemaId(long subproblemaId) {
		this.subproblemaId = subproblemaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
