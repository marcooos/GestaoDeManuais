package br.com.pcsocial.gestao.dao;

/**
 *
 * @author Marcos Luiz
 * 10/11/2013
 */
import java.util.List;

public interface DaoBase {

	public void save(Object object);

	public List<Object> list(String object);

	public void remove(Object object);

	public void update(Object object);
}
