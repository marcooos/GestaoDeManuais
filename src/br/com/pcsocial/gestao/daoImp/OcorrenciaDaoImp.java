package br.com.pcsocial.gestao.daoImp;

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
}
