package br.com.pcsocial.gestao.controle;

import br.com.pcsocial.gestao.daoImp.OcorrenciaDaoImp;
import br.com.pcsocial.gestao.modelo.Ocorrencia;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class OcorrenciaControler {
	private OcorrenciaDaoImp ocorrencia;

	public OcorrenciaControler() {
		ocorrencia = new OcorrenciaDaoImp();
	}

	public ObjectTableModel<Ocorrencia> montarTabela(String text, long valor) {
		try {
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Ocorrencia.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Ocorrencia> tableModel = new ObjectTableModel<Ocorrencia>(
					resolver, "id,dataOperacao,descricao");

			// Here we use the list to be the data of the table.
			// System.out.println(ocorrencia.list(text,valor).toString());
			tableModel.setData(ocorrencia.list(text, valor));
			return tableModel;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public Ocorrencia buscarOcorrenciaId(Long id) {
		try {
			Ocorrencia p = new Ocorrencia();
			p = (Ocorrencia) ocorrencia.getById(id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean adicionarOcorrencia(Ocorrencia p) {
		try {
			ocorrencia.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterarOcorrencia(Ocorrencia p) {
		try {
			ocorrencia.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluirOcorrencia(Long id) {
		ocorrencia.remove(ocorrencia.getById(id));
		return true;
	}
}
