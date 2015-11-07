package tn.edu.esprit.sigma.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tn.edu.esprit.sigma.client.delegate.CarServicesDelegate;
import tn.edu.esprit.sigma.client.delegate.FlightServicesDelegate;
import tn.edu.esprit.sigma.client.delegate.PassengerServicesDelegate;
import entities.Car;
import entities.CarCategory;
import entities.CarState;
import entities.Flight;
import entities.FlightCategory;
import entities.Passenger;
import entities.Ticket;

public class TestERP {

	static Passenger passenger = new Passenger("jridi", "nada", "F", null,
			null, null, null);
	//static Flight flight = new Flight(null, null, "espagne", 200, "airlines",FlightCategory.BUSINESS, 120);
	//static Ticket newTicket = new Ticket(120, passenger, flight);
	static List<Passenger> passengers;
	static Car car = new Car("gcvgv", "dff", CarCategory.CLASSIC,
			CarState.AVAILABLE, null, null, null, 60);
	static Car car2 = CarServicesDelegate.doFindCarById(1);
	// static List<Car> cars=CarServicesDelegate.doFindAllCars();
	static Date date1, date2, date3, date4;
	static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public static void main(String[] args) {

		try {
			date1 = df.parse("01-11-2015");
			date2 = df.parse("06-11-2015");
			date3 = df.parse("08-11-2015");
			date4 = df.parse("15-11-2015");
		} catch (ParseException e) {
			e.fillInStackTrace();
		}

		PassengerServicesDelegate.doAddPassenger(passenger);
		//FlightServicesDelegate.doAddFlight(flight);
		// TicketServicesDelegate.doBookTicket(newTicket, passenger.getId(),
		// flight.getId());
		// newTicket.setPrice(120);
		// TicketServicesDelegate.doAddTicket(newTicket);
		// CarServicesDelegate.doAddCar(car);
		// passengers.add(passenger);
		// CarServicesDelegate.doRentCar(passengers, car, null, null);

		// car2.setStartDate(date1);
		// car2.setEndDate(date2);
		// CarServicesDelegate.doUpdateCar(car2);

		// System.out.println(car2.getBrand().toString());

		// passengers.add(passenger);
		// CarServicesDelegate.doRentCar(passengers, car2, date3, date4);
		// CarServicesDelegate.doRentCar(passengers, car2, date3, date4);

	}

}
