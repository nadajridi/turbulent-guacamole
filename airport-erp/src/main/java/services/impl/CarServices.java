package services.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Car;
import entities.Passenger;
import services.interfaces.CarServicesLocal;
import services.interfaces.CarServicesRemote;

/**
 * Session Bean implementation class CarServices
 */
@Stateless
public class CarServices implements CarServicesRemote, CarServicesLocal {

	
	@PersistenceContext
	private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public CarServices() {}

	@Override
	public Boolean addCar(Car car) {
		Boolean b = false;
		try {
			entityManager.persist(car);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean updateCar(Car car) {
		Boolean b = false;
		try {
			entityManager.merge(car);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deleteCar(Car car) {
		Boolean b = false;
		try {
			car = findCarById(car.getId());
			entityManager.remove(car);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Car findCarById(Integer id) {
		return entityManager.find(Car.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAllCars() {
		String jpql = "select c from t_car c";
		Query query =entityManager.createQuery(jpql);
		return  query.getResultList();
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Boolean rentCar(List<Passenger> passenger, Car car, Date startDate, Date endDate) {
		Boolean result = false;
		Date today = new java.util.Date();
		Date endRantal = car.getEndDate();
		
		if (startDate.compareTo(today) < 0)
			result = false;
		
		else 
			{
			if (today.compareTo(endRantal) > 0)
			{
			car.setStartDate(startDate);
			car.setEndDate(endDate);
			car.setState(car.getState().RENTED);
			//car.setPassengers(passenger);
			((Passenger)passenger).setCars((List<Car>) car);
			
			
			
			result = updateCar(car);
			}
			
			}
		
		return result;
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAllCarsByPassengerId(Integer idPassenger) {
		String jpql = "select c from t_car c  where :param member of c.passengers";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idPassenger);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAllCarsByCarRentalAgencyId(Integer carRentalAgencyId) {
		String jpql = "select c from t_car c where c.idCarRentalAgency=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", carRentalAgencyId);
		return query.getResultList();
	}

	

	

}
