package br.com.pcsocial.gestao.daoImp;

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
}
