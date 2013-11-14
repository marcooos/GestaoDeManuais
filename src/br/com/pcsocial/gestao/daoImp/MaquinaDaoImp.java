package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Maquina;
import br.com.pcsocial.gestao.util.HibernateUtil;

public class MaquinaDaoImp extends DaoBaseImp {

	public Maquina getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Maquina maquina = (Maquina) session.load(Maquina.class, id);
		maquina.toString();  
        session.flush();  
        session.close(); 
		return maquina;
	}
	
	@SuppressWarnings("unchecked")
	public List<Maquina> list(String text, long valor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        if (text.equals("")&&(valor == 0)){
        	Query q = session.createQuery("from Maquina" );
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        } else
		if (text.equals("")){
        	Query q = session.createQuery("from Maquina where id = :valor" );
            q.setLong("valor", valor);
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        }
        if (valor == 0){
        	Query q = session.createQuery("from Maquina where nome like :text " +
        			"or modelo like :text");
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
