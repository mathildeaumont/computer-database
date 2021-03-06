package com.excilys.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.model.QCompany;
import com.excilys.model.QComputer;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

	/** The entity manager factory. */
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#getLength()
	 */
	public int getLength() {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputer computer = QComputer.computer;
	    query = query.from(computer);
	    return (int) query.count();
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#getAll()
	 */
	public List<Computer> getAll() {	
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("ComputerDatabase_PU");
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputer computer = QComputer.computer;

		List<Computer> computers = query.from(computer).list(computer);
		em.close();
		return computers;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#getAllByPage(com.excilys.model.Page, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputer computer = QComputer.computer;
	    QCompany company = QCompany.company;
		query = query.from(computer).leftJoin(computer.company, company)
										.where(computer.name.contains(search).or(company.name.contains(search)))
										.limit(page.getNbResults())
										.offset(page.getOffset());
		if (!order.isEmpty()) {
			order = order.split("\\.")[1];
			PathBuilder orderBy;
			if (order.contains("_")) {
				orderBy = new PathBuilder(Company.class, "company");
				order = order.split("_")[1];
			} else {
				orderBy = new PathBuilder(Computer.class, "computer");
			}
			query = query.orderBy(new OrderSpecifier<Comparable>(direction.equals("desc") ? com.mysema.query.types.Order.DESC : 
			com.mysema.query.types.Order.ASC, orderBy.get(order)));
		}
		return query.list(computer);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#getLength(java.lang.String)
	 */
	public int getLength(String search) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputer computer = QComputer.computer;
	    QCompany company = QCompany.company;
	    query = query.from(computer).leftJoin(computer.company, company)
				.where(computer.name.contains(search).or(company.name.contains(search)));
	    return (int) query.count();
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#getDetails(long)
	 */
	public Computer getDetails(long idComputer) {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QComputer computer = QComputer.computer;
	    QCompany company = QCompany.company;
	    query = query.from(computer).leftJoin(computer.company, company).where(computer.id.eq(idComputer));
	    return query.uniqueResult(computer);
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#create(com.excilys.model.Computer)
	 */
	@Transactional
	public void create(Computer compu) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(compu);						
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#update(com.excilys.model.Computer)
	 */
	@Transactional
	public void update(Computer compu) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		QComputer computer = QComputer.computer;
		try {
			transaction.begin();
			new JPAUpdateClause(em, computer).where(computer.id.eq(compu.getId()))
											.set(computer.name, compu.getName())
											.set(computer.introducedDate, compu.getIntroducedDate())
											.set(computer.discontinuedDate, compu.getDiscontinuedDate())
											.set(computer.company, compu.getCompany())
											.execute();	
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#delete(long)
	 */
	@Transactional
	public void delete(long computerId) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		QComputer computer = QComputer.computer;
		try {
			transaction.begin();
			new JPADeleteClause(em, computer).where(computer.id.eq(computerId)).execute();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.persistence.ComputerDao#deleteByCompanyId(long)
	 */
	@Transactional
	public void deleteByCompanyId(long companyId) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		QComputer computer = QComputer.computer;
		try {
			transaction.begin();
			new JPADeleteClause(em, computer).where(computer.company.id.eq(companyId)).execute();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
}
