package br.com.pcsocial.gestao.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "documento")
public class Documento implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "Documento")
	private String documento;	
	@Lob
	@Column(columnDefinition="LONGBLOB")
	@Resolvable(colName = "anexo")
	private byte[] anexo;
	@ManyToMany(mappedBy="documento",fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private List<Maquina> maquina;
	
	public Documento() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public List<Maquina> getMaquina() {
		return maquina;
	}

	public void setMaquina(List<Maquina> maquina) {
		this.maquina = maquina;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
