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
 * @author Marcos Luiz
 * 10/11/2013
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "E-mail")
	private String email;
	@Column(nullable = false)
	@Resolvable(colName = "Senha")
	private String senha;
	@Resolvable(colName = "Nome")
	private String nome;
	@Resolvable(colName = "Sobrenome")
	private String sobreNome;
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private List<Permissoes> permissoes;

	public Pessoa() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}		

}
