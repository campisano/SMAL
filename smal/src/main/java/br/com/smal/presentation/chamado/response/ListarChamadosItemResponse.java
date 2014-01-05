package br.com.smal.presentation.chamado.response;

import java.util.Date;

public class ListarChamadosItemResponse {
	private long id;
	private long protocolo;
	private Date data_hora_abertura;
	private Date data_hora_fechamento;
	private String descricao;
	private long abridorId;
	private long atendenteId;
	private long designadorId;
	private int status;
	private long subproblemaId;
	private long maquinaId;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(long protocolo) {
		this.protocolo = protocolo;
	}
	public Date getData_hora_abertura() {
		return data_hora_abertura;
	}
	public void setData_hora_abertura(Date data_hora_abertura) {
		this.data_hora_abertura = data_hora_abertura;
	}
	public Date getData_hora_fechamento() {
		return data_hora_fechamento;
	}
	public void setData_hora_fechamento(Date data_hora_fechamento) {
		this.data_hora_fechamento = data_hora_fechamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getAbridorId() {
		return abridorId;
	}
	public void setAbridorId(long abridorId) {
		this.abridorId = abridorId;
	}
	public long getAtendenteId() {
		return atendenteId;
	}
	public void setAtendenteId(long atendenteId) {
		this.atendenteId = atendenteId;
	}
	public long getDesignadorId() {
		return designadorId;
	}
	public void setDesignadorId(long designadorId) {
		this.designadorId = designadorId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getSubproblemaId() {
		return subproblemaId;
	}
	public void setSubproblemaId(long subproblemaId) {
		this.subproblemaId = subproblemaId;
	}
	public long getMaquinaId() {
		return maquinaId;
	}
	public void setMaquinaId(long maquinaId) {
		this.maquinaId = maquinaId;
	}
}
