package br.com.pcsocial.gestao.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.towel.el.annotation.Resolvable;

/**
 * 
 * @author Marcos Luiz 10/11/2013
 */
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "descricao")
	private String descricao;
	@OneToOne
	private Indices indices;
	@Resolvable(colName = "Data")
	@Temporal(javax.persistence.TemporalType.DATE)
    private Date dataOperacao = new Date(System.currentTimeMillis());
	@OneToOne
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Indices getIndices() {
		return indices;
	}

	public void setIndices(Indices indices) {
		this.indices = indices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
