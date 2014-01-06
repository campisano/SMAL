package br.com.smal.presentation.subproblema.request;

public class CadastrarSubproblemaRequest {
	private String nome;
	private long problemaId;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getProblemaId() {
		return problemaId;
	}
	public void setProblemaId(long problemaId) {
		this.problemaId = problemaId;
	}
}
