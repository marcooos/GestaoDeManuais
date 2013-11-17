package br.com.pcsocial.gestao.controle;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import br.com.pcsocial.gestao.daoImp.DocumentoDaoImp;
import br.com.pcsocial.gestao.modelo.Documento;

public class DocumentoControler {
	private DocumentoDaoImp documento;

	public DocumentoControler() {
		documento = new DocumentoDaoImp();
	}

	public ObjectTableModel<Documento> montarTabela(String text, long valor) {
		try {
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(
					Documento.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Documento> tableModel = new ObjectTableModel<Documento>(
					resolver, "id,documento,arquivo");

			// Here we use the list to be the data of the table.
			// System.out.println(documento.list(text,valor).toString());
			tableModel.setData(documento.list(text, valor));
			return tableModel;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public Documento buscarDocumentoId(Long id) {
		try {
			Documento p = new Documento();
			p = (Documento) documento.getById(id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean adicionarDocumento(Documento p) {
		try {
			documento.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterarDocumento(Documento p) {
		try {
			documento.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluirDocumento(Long id) {
		documento.remove(documento.getById(id));
		return true;
	}

	public boolean validaDocumentoCadastrado(String nomeArquivo) {
		if (documento.validaDocumentoCadastrado(nomeArquivo)) {
			return true;
		} else {
			return false;
		}
	}

}
