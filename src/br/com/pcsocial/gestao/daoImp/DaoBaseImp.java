package br.com.pcsocial.gestao.daoImp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.pcsocial.gestao.dao.DaoBase;
import br.com.pcsocial.gestao.util.HibernateUtil;

public class DaoBaseImp implements DaoBase{
	public void save(Object object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(object);
		t.commit();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public List<Object> list(String object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from"+ object).list();
		try {
			return lista;
		} finally {
			session.close();
		}
	}

	public void remove(Object object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(object);
		t.commit();
		session.close();
	}

	public void update(Object object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(object);
		t.commit();
		session.close();
	}
}
