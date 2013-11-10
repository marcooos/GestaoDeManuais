package br.com.pcsocial.gestao.modelo;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import com.towel.el.annotation.Resolvable;

/**
 * 
 * @author Marcos Luiz 10/11/2013
 */
@Entity
@Table(name = "permissoes")
public class Permissoes implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "Funcionalidade")
	private String funcionalidade;
	@Column(nullable = false)
	@Resolvable(colName = "acesso")
	private String acesso;
	@ManyToMany(mappedBy="permissoes",fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private List<Pessoa> pessoa;

	public Permissoes() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}	

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
