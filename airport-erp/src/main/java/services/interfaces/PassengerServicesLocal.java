package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Passenger;

@Local
public interface PassengerServicesLocal {
	
	Boolean addPassenger(Passenger passenger);
	Boolean updatePassenger(Passenger passenger);
	Boolean deletePassenger(Passenger passenger);
	Passenger findPassengerById(Integer id);
	List<Passenger> findAllPassengers();
	List<Passenger> findAllPassengersByCarRegistrationNumber(Integer carRegistrationNumber);
	List<Passenger> findAllPassengersByFlightId(Integer flightId);

}
