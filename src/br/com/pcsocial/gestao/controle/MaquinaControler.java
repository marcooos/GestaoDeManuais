package br.com.pcsocial.gestao.controle;

import br.com.pcsocial.gestao.daoImp.MaquinaDaoImp;
import br.com.pcsocial.gestao.modelo.Maquina;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class MaquinaControler {
	private MaquinaDaoImp maquina;

	public MaquinaControler() {
		maquina = new MaquinaDaoImp();
	}

	public ObjectTableModel<Maquina> montarTabela(String text, long valor) {
		try {
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Maquina.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Maquina> tableModel = new ObjectTableModel<Maquina>(
					resolver, "id,nome,modelo");

			// Here we use the list to be the data of the table.
			// System.out.println(maquina.list(text,valor).toString());
			tableModel.setData(maquina.list(text, valor));
			return tableModel;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public Maquina buscarMaquinaId(Long id) {
		try {
			Maquina p = new Maquina();
			p = (Maquina) maquina.getById(id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean adicionarMaquina(Maquina p) {
		try {
			maquina.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean alterarMaquina(Maquina p) {
		try {
			maquina.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluirMaquina(Long id) {
		maquina.remove(maquina.getById(id));
		return true;
	}
}
