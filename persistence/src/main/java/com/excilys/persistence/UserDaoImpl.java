package com.excilys.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.QUser;
import com.excilys.model.User;
import com.mysema.query.jpa.impl.JPAQuery;

public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Transactional
	public void createUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(user);						
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	@Override
	public User getUser(String login) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QUser user = QUser.user;
		query = query.from(user).where(user.username.eq(login));
		return query.uniqueResult(user);
	}

}
