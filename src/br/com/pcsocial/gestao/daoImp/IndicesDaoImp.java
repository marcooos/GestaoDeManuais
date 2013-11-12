package br.com.pcsocial.gestao.daoImp;

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

}
