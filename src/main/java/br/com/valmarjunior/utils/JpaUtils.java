package main.java.br.com.valmarjunior.utils;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.Properties;

public final class JpaUtils {
	private static final String PERSISTENCE_UNIT = "Balafhar";
	private static final Logger LOGGER = LoggerFactory.getLogger( JpaUtils.class );
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<>();
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static Properties properties = ApplicationUtils.getApplicationProperties();
	
	private JpaUtils() {
	}
	
	public static void openEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT, properties );
			LOGGER.debug( "Entity Manager Factory criado com sucesso!" );
		}
	}
	
	public static EntityManager openEntityManager() throws NullPointerException {
		
		try {
			
			EntityManager entityManager = threadEntityManager.get();
			
			if (entityManager == null || !entityManager.isOpen()) {
				
				entityManager = entityManagerFactory.createEntityManager();
				LOGGER.debug( String.valueOf( entityManager ) );
			}
			LOGGER.info( "openEntityManagerFactory criado com sucesso!" );
			JpaUtils.entityManager = entityManager;
			return entityManager;
		} catch (HibernateException e) {
			LOGGER.error( String.valueOf( e.getCause() ) );
		}
		return null;
	}
	
	public static void updateDataFromDatabase() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = entityManagerFactory.createEntityManager();
			LOGGER.debug( entityManager.toString() );
		}
		entityManager.getTransaction().begin();
		Connection connection = entityManager.unwrap( Connection.class );
		System.out.println( connection );
		entityManager.getTransaction().commit();
		
	}
	
	public static void closeEntityManager() {
		
		EntityManager entityManager = threadEntityManager.get();
		if (entityManager != null) {
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			
			if (entityTransaction.isActive()) {
				entityTransaction.commit();
			}
			
			entityManager.close();
			System.out.println( "entityManager -> fechado" );
			threadEntityManager.set( null );
		}
	}
	
	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
			LOGGER.info( "Entity Manager Factory encerrado" );
			
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			openEntityManager();
		}
		return entityManager;
	}
	
}
