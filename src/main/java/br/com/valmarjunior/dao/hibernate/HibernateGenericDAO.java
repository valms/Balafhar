package main.java.br.com.valmarjunior.dao.hibernate;

import main.java.br.com.valmarjunior.dao.GenericDAO;
import main.java.br.com.valmarjunior.utils.JpaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	protected static final Logger LOGGER = LoggerFactory.getLogger( HibernateGenericDAO.class );
	protected EntityManager entityManager = JpaUtils.getEntityManager();
	
	@Override
	public T save(T entity) {
		if (isEntityManagerisClosed( entityManager )) {
			this.entityManager = JpaUtils.openEntityManager();
		}
		
		LOGGER.debug( entity.toString() );
		T savedEntity = null;
		entityManager.getTransaction().begin();
		savedEntity = entityManager.merge( entity );
		entityManager.getTransaction().commit();
		JpaUtils.closeEntityManager();
		return savedEntity;
	}
	
	@Override
	public void remove(T entity) {
		if (isEntityManagerisClosed( entityManager )) {
			this.entityManager = JpaUtils.openEntityManager();
		}
		
		LOGGER.debug( entity.toString() );
		entityManager.getTransaction().begin();
		entityManager.remove( entity );
		entityManager.getTransaction().commit();
		JpaUtils.closeEntityManager();
		System.out.println( "entityManager -> fechado" );
	}
	
	@Override
	public T getById(Class<T> entityClass, ID pk) {
		if (isEntityManagerisClosed( entityManager )) {
			this.entityManager = JpaUtils.openEntityManager();
		}
		
		T entityClassResult;
		try {
			entityClassResult = entityManager.find( entityClass, pk );
			JpaUtils.closeEntityManager();
			return entityClassResult;
		} catch (NoResultException e) {
			LOGGER.error( e.getMessage() );
			return null;
		}
	}
	
	@Override
	public List<T> getAll(Class<T> entityClass) {
		if (isEntityManagerisClosed( entityManager )) {
			this.entityManager = JpaUtils.openEntityManager();
		}
		
		List<T> entityClassResults;
		entityClassResults = entityManager.createQuery( "select o from " + entityClass.getSimpleName() + " o" )
			                     .getResultList();
		JpaUtils.closeEntityManager();
		return entityClassResults;
	}
	
	protected boolean isEntityManagerisClosed(EntityManager entityManager) {
		return !entityManager.isOpen();
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
