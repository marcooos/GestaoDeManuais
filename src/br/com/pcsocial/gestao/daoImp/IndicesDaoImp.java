package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.util.HibernateUtil;

public class IndicesDaoImp extends DaoBaseImp {

	public Indices getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Indices indices = (Indices) session.load(Indices.class, id);
		indices.toString();  
        session.flush();  
        session.close(); 
		return indices;
	}

	@SuppressWarnings("unchecked")
	public List<Indices> list(String text, long valor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        if (text.equals("")&&(valor == 0)){
        	Query q = session.createQuery("from Indices" );
            try {
            	q.list().toString();
    			return q.list();
    		} finally {
                session.close();
            }
        } else
		if (text.equals("")){
        	Query q = session.createQuery("from Indices where id = :valor" );
            q.setLong("valor", valor);
            try {
            	q.list().toString();
    			return q.list();
    		} finally {
                session.close();
            }
        }
        if (valor == 0){
        	Query q = session.createQuery("from Indices where indice like :text ");
            q.setString("text", '%' + text.toLowerCase() + '%');
            try {
            	q.list().toString();
    			return q.list();
    		} finally {
                session.close();
            }
        }
		return null;
	}
	
	public boolean validaIndiceCadastrado(String nomeIndice){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from Indices where indice like :text ");
        q.setString("text", '%' + nomeIndice.toLowerCase() + '%');
        if (q.list().isEmpty()) {
        	session.close();
        	return true;
        } else {
        	session.close();
        	return false;        	
        }
                
	}

}
