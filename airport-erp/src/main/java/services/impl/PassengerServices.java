package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.PassengerServicesLocal;
import services.interfaces.PassengerServicesRemote;
import entities.Passenger;


/**
 * Session Bean implementation class PassengerServices
 */
@Stateless
public class PassengerServices implements PassengerServicesRemote, PassengerServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public PassengerServices() {}

	@Override
	public Boolean addPassenger(Passenger passenger) {
		Boolean b = false;
		try {
			entityManager.persist(passenger);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean updatePassenger(Passenger passenger) {
		Boolean b = false;
		try {
			entityManager.merge(passenger);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deletePassenger(Passenger passenger) {
		Boolean b = false;
		try {
			passenger = findPassengerById(passenger.getId());
			entityManager.remove(passenger);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Passenger findPassengerById(Integer id) {
		return entityManager.find(Passenger.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassengers() {
		String jpql = "select p from t_user p";
		Query query =entityManager.createQuery(jpql);
		return  query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassengersByCarRegistrationNumber(
			Integer carRegistrationNumber) {
		String jpql = "select p from t_user p  where :param member of p.cars";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", carRegistrationNumber);
		return query.getResultList();
	}

	


	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassengersByFlightId(Integer flightId) {
		String jpql = "select p from t_user p join p.tickets t where t.idFlight=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", flightId);
		return query.getResultList();
	}

}
