package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Pessoa;
import br.com.pcsocial.gestao.util.HibernateUtil;


public class PessoaDaoImp extends DaoBaseImp{
	
	public Pessoa getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoa = (Pessoa) session.load(Pessoa.class, id);
		pessoa.toString();  
        session.flush();  
        session.close(); 
		return pessoa;
	}
	
	public boolean validarLogin(String email, String senha) {
        boolean retorno;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Pessoa where email = :email and senha = :senha");
        q.setString("email", email);
        q.setString("senha", senha);
        retorno = q.list().isEmpty();
        try {
            return retorno;
        } finally {
            session.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> list(String text, long valor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        if (text.equals("")&&(valor == 0)){
        	Query q = session.createQuery("from Pessoa" );
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        } else
		if (text.equals("")){
        	Query q = session.createQuery("from Pessoa where id = :valor" );
            q.setLong("valor", valor);
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        }
        if (valor == 0){
        	Query q = session.createQuery("from Pessoa where nome like :text " +
        			"or sobreNome like :text or email like :text");
            q.setString("text", '%' + text.toLowerCase() + '%');
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        }
		return null;
	}

}
