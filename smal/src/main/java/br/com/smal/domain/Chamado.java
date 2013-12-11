package br.com.smal.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chamado")
public class Chamado {

	@Id
	@GeneratedValue
	private Long id;
	private Long protocolo;
	private Date data_hora_abertura;
	private Date data_hora_fechamento;
	private String descricao;
	@ManyToOne(optional = false)
	private Usuario abridor;
	@ManyToOne(optional = true)
	private Tecnico atendente;
	@ManyToOne(optional = true)
	private Administrador designador;
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	@ManyToOne(optional = false)
	private Subproblema subproblema;

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

	public Date getDataHoraAbertura() {
		return data_hora_abertura;
	}

	public void setDataHoraAbertura(Date data_hora_abertura) {
		this.data_hora_abertura = data_hora_abertura;
	}

	public Date getDataHoraFechamento() {
		return data_hora_fechamento;
	}

	public void setDataHoraFechamento(Date data_hora_fechamento) {
		this.data_hora_fechamento = data_hora_fechamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getAbridor() {
		return abridor;
	}

	public void setAbridor(Usuario abridor) {
		this.abridor = abridor;
	}

	public Tecnico getAtendente() {
		return atendente;
	}

	public void setAtendente(Tecnico atendente) {
		this.atendente = atendente;
	}

	public Administrador getDesignador() {
		return designador;
	}

	public void setDesignador(Administrador designador) {
		this.designador = designador;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Subproblema getSubproblema() {
		return subproblema;
	}

	public void setSubproblema(Subproblema subproblema) {
		this.subproblema = subproblema;
	}
}
