package br.com.pcsocial.gestao.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

/**
 * 
 * @author Marcos Luiz
 * 10/11/2013
 */
@Entity
@Table(name = "indices")
public class Indices {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Resolvable(colName = "Id")
	private long id;
	@Column(nullable = false)
	@Resolvable(colName = "Indice")
	private String indice;
	@Resolvable(colName = "Descricao")
	private String descricao;
	private int pagina;
	
	@OneToOne
	private Documento documento;	
	
	
	public Indices () {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPagina() {
		return pagina;
	}
	
	public String getPaginaStr() {
		return String.valueOf(pagina);
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	public void setPaginaStr(String pagina) {
		this.pagina = Integer.parseInt(pagina);
	}
	
}
