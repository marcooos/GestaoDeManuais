package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Ocorrencia;
import br.com.pcsocial.gestao.util.HibernateUtil;

public class OcorrenciaDaoImp extends DaoBaseImp {

	public Ocorrencia getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Ocorrencia ocorrencia = (Ocorrencia) session.load(Ocorrencia.class, id);
		ocorrencia.toString();  
        session.flush();  
        session.close(); 
		return ocorrencia;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ocorrencia> list(String text, long valor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        if (text.equals("")&&(valor == 0)){
        	Query q = session.createQuery("from Ocorrencia" );
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        } else
		if (text.equals("")){
        	Query q = session.createQuery("from Ocorrencia where id = :valor" );
            q.setLong("valor", valor);
            try {
    			return q.list();
    		} finally {
                session.close();
            }
        }
        if (valor == 0){
        	Query q = session.createQuery("from Ocorrencia where descricao like :text " +
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
