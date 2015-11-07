package services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Car;
import entities.Passenger;

@Remote
public interface CarServicesRemote {
	
	Boolean addCar (Car car);
	Boolean updateCar (Car car);
	Boolean deleteCar (Car car);
	Car findCarById (Integer id);
	List<Car> findAllCars();
	List<Car> findAllCarsByPassengerId(Integer idPassenger);
	List<Car> findAllCarsByCarRentalAgencyId(Integer carRentalAgencyId);
	Boolean rentCar(List<Passenger> passenger, Car car, Date startDate, Date endDate);
	

}
