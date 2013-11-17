package br.com.pcsocial.gestao.controle;

import br.com.pcsocial.gestao.daoImp.IndicesDaoImp;
import br.com.pcsocial.gestao.modelo.Indices;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class IndicesControler {
private IndicesDaoImp indices;
	
	public IndicesControler() {
		indices = new IndicesDaoImp();
	}

	public ObjectTableModel<Indices> montarTabela(String text, long valor) {
		try {
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Indices.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Indices> tableModel = new ObjectTableModel<Indices>(
					resolver, "id,indice,documento.documento,documento.arquivo");

			// Here we use the list to be the data of the table.
			// System.out.println(indices.list(text,valor).toString());
			tableModel.setData(indices.list(text, valor));
			return tableModel;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public Indices buscarIndicesId(Long id) {
		try {
			Indices p = new Indices();
			p = (Indices) indices.getById(id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean adicionarIndices(Indices p) {
		try {
			indices.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterarIndices(Indices p) {
		try {
			indices.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluirIndices(Long id) {
		indices.remove(indices.getById(id));
		return true;
	}
	
	public boolean validaIndiceCadastrado(String nomeIndice) {
		if (indices.validaIndiceCadastrado(nomeIndice)) {
			return true;
		} else {
			return false;
		}
	}

}
