package br.com.pcsocial.gestao.daoImp;

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

}
