package tn.edu.esprit.sigma.client.delegate;

import java.util.List;

import services.interfaces.PassengerServicesRemote;
import tn.edu.esprit.sigma.client.locator.ServiceLocator;
import entities.Passenger;

public class PassengerServicesDelegate {
	
	//private static PassengerServicesRemote remote;
	private static final String jndi="airport-erp/PassengerServices!services.interfaces.PassengerServicesRemote";
	
	private static PassengerServicesRemote getProxy()
	{
		return (PassengerServicesRemote) ServiceLocator.getInstance().getProxy(jndi);
	}
	
public static	Boolean doAddPassenger(Passenger passenger)
	{
		return getProxy().addPassenger(passenger);
	}
public static	Boolean doUpdatePassenger(Passenger passenger)
	{
		return getProxy().updatePassenger(passenger);
	}
public static	Boolean doDeletePassenger(Passenger passenger)
	{
		return getProxy().deletePassenger(passenger);
	}
public static	Passenger doFindPassengerById(Integer idPassenger)
	{
		return getProxy().findPassengerById(idPassenger);
	}
public static	List<Passenger> doFindAllPassengers()
	{
		return getProxy().findAllPassengers();
	}
public static	List<Passenger> doFindAllPassengersByCarRegistrationNumber(Integer carRegistrationNumber)
	{
		return getProxy().findAllPassengersByCarRegistrationNumber(carRegistrationNumber);
	}
public static	List<Passenger> doFindAllPassengersByFlightId(Integer flightId)
	{
		return getProxy().findAllPassengersByFlightId(flightId);
	}

}
