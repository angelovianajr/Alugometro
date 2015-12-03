package br.com.alugometro.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Estado")
public class Estado {

	@Id
	@Column(name = "IdEstado")
	private Long idEstado;
	
	@Column(name = "nome")
	@Length(max = 300)
	@Basic(optional = false)
	private String nome;
	
	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Federacao getIdFederacao() {
		return idFederacao;
	}

	public void setIdFederacao(Federacao idFederacao) {
		this.idFederacao = idFederacao;
	}

	@Column(name = "UF")
	@Length(max = 2)
	@Basic(optional = false)
	private String uf;
	
	@JoinColumn(name = "IdFederacao")
	@Basic(optional = false)
	private Federacao idFederacao;
	
}