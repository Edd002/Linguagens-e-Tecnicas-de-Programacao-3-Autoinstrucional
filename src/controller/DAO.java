package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Eduardo Augusto
 *
 * Classe DAO.
 */
public class DAO {
	private static SessionFactory sessionFactory;

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param
	 * @exception
	 * 
	 * Gerar fábrica de sessões Hibernate.
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		}
		return sessionFactory;
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param Object
	 * @exception HibernateException, Exception
	 * 
	 * Persistir um objeto no banco de dados.
	 */
	public static void insert(Object object) throws HibernateException, Exception {
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(object);
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			throw new HibernateException(hibernateException);
		} catch (Exception exception) {
			if (transaction != null)
				transaction.rollback();
			throw new Exception(exception);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return
	 * @param String, Class<T>
	 * @exception HibernateException, Exception
	 * 
	 * Atualizar/Deletar um objeto do banco de dados.
	 */
	public static <T> void update(String hql) throws HibernateException, Exception {
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.createQuery(hql).executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			throw new HibernateException(hibernateException);
		} catch (Exception exception) {
			if (transaction != null)
				transaction.rollback();
			throw new Exception(exception);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return ArrayList<T>
	 * @param String, Class<T>
	 * @exception HibernateException, Exception
	 * 
	 * Selecionar registros do banco de dados.
	 */
	public static <T> ArrayList<T> select(String hql, Class<T> clazz) throws HibernateException, Exception {
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<T> list = session.createQuery(hql, clazz).getResultList();
			ArrayList<T> arrayList = new ArrayList<>(list);
			transaction.commit();
			return arrayList;
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			throw new HibernateException(hibernateException);
		} catch (Exception exception) {
			if (transaction != null)
				transaction.rollback();
			throw new Exception(exception);
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * @author Eduardo Augusto
	 * @return Object
	 * @param String, Class<T>
	 * @exception HibernateException, Exception
	 * 
	 * Selecionar um registro do banco de dados.
	 */
	public static <T> Object selectUniqueResult(String hql, Class<T> clazz) throws HibernateException, Exception {
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Object object = session.createQuery(hql, clazz).uniqueResult();
			transaction.commit();
			return object;
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			throw new HibernateException(hibernateException);
		} catch (Exception exception) {
			if (transaction != null)
				transaction.rollback();
			throw new Exception(exception);
		} finally {
			if (session != null)
				session.close();
		}
	}
}