package br.com.pcsocial.gestao.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@OneToOne
	private Maquina maquina;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	@Resolvable(colName = "anexo")
	private byte[] anexo;
	@Resolvable(colName="Arquivo")
	private String arquivo;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

}
