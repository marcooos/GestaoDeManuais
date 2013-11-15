package br.com.pcsocial.gestao.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "maquina")
public class Maquina implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "Nome")
	private String nome;
	@Column(nullable = false)
	@Resolvable(colName = "Modelo")
	private String modelo;
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private List<Documento> documento;

	public Maquina() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}		

}
