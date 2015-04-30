package com.excilys.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImpl.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

	/** The entity manager factory. */
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/* (non-Javadoc)
	 * @see com.excilys.persistence.CompanyDao#getAll()
	 */
	public List<Company> getAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompany company = QCompany.company;

		List<Company> companies = query.from(company).list(company);
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.CompanyDao#delete(long)
	 */
	@Transactional
	public void delete(long companyId) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		QCompany company = QCompany.company;
		try {
			transaction.begin();
			new JPADeleteClause(em, company).where(company.id.eq(companyId)).execute();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
}
