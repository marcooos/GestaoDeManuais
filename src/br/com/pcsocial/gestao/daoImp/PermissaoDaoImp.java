package br.com.pcsocial.gestao.daoImp;

import org.hibernate.Session;

import br.com.pcsocial.gestao.modelo.Permissoes;
import br.com.pcsocial.gestao.util.HibernateUtil;

public class PermissaoDaoImp extends DaoBaseImp {

	public Permissoes getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Permissoes permissoes = (Permissoes) session.load(Permissoes.class, id);
		permissoes.toString();  
        session.flush();  
        session.close(); 
		return permissoes;
	}
}
