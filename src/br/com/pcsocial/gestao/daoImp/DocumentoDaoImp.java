package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.util.HibernateUtil;


public class DocumentoDaoImp extends DaoBaseImp {

	public Documento getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Documento documento = (Documento) session.load(Documento.class, id);
		documento.toString();  
        session.flush();  
        session.close(); 
		return documento;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> list(String text, long valor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        if (text.equals("")&&(valor == 0)){
        	Query q = session.createQuery("from Documento" );
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        } else
		if (text.equals("")){
        	Query q = session.createQuery("from Documento where id = :valor" );
            q.setLong("valor", valor);
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        }
        if (valor == 0){
        	Query q = session.createQuery("from Documento where documento like :text ");
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
