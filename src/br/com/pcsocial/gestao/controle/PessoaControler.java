package br.com.pcsocial.gestao.controle;

import br.com.pcsocial.gestao.daoImp.PessoaDaoImp;
import br.com.pcsocial.gestao.modelo.Pessoa;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class PessoaControler {
	private PessoaDaoImp pessoa;
	
	public PessoaControler() {
		pessoa = new PessoaDaoImp();
	}

	public ObjectTableModel<Pessoa> montarTabela(String text, long valor) {
		try {			
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Pessoa.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Pessoa> tableModel = new ObjectTableModel<Pessoa>(
					resolver, "id,email,nome,sobreNome");

			// Here we use the list to be the data of the table.
			// System.out.println(pessoa.list(text,valor).toString());
			tableModel.setData(pessoa.list(text, valor));
			return tableModel;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public Pessoa buscarPessoaId(Long id) {
		try {
			Pessoa p = new Pessoa();			
			p = (Pessoa) pessoa.getById(id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean adicionarPessoa(Pessoa p) {
		try {			
			pessoa.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean alterarPessoa(Pessoa p) {
		try {
			pessoa.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean excluirPessoa(Long id) {		
		pessoa.remove(pessoa.getById(id));
		return true;
	}
	
	public boolean consultarPessoaEmail(String email){
		if (pessoa.validarLogin(email)){
			return true;
		} else return false;
	}
}
