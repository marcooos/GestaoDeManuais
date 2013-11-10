package br.com.pcsocial.gestao.util;


import br.com.pcsocial.gestao.modelo.Documento;
import br.com.pcsocial.gestao.modelo.Indices;
import br.com.pcsocial.gestao.modelo.Maquina;
import br.com.pcsocial.gestao.modelo.Ocorrencia;
import br.com.pcsocial.gestao.modelo.Permissoes;
import br.com.pcsocial.gestao.modelo.Pessoa;


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Jos? Alexandre
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }
    

    public static void main(String args[]) {
        getSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Pessoa.class)
                .addAnnotatedClass(Permissoes.class)
                .addAnnotatedClass(Documento.class)
                .addAnnotatedClass(Maquina.class)
                .addAnnotatedClass(Indices.class)
                .addAnnotatedClass(Ocorrencia.class)
                ;
                sessionFactory = ac.configure().buildSessionFactory();
            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}