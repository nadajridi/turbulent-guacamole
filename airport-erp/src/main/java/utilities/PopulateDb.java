package utilities;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import services.interfaces.FlightServicesLocal;
import services.interfaces.PassengerServicesLocal;
import entities.Flight;
import entities.FlightCategory;
import entities.Passenger;

/**
 * Session Bean implementation class PopulateDb
 */
@Singleton
@LocalBean
@Startup
public class PopulateDb {
	@EJB
	private PassengerServicesLocal passengerServicesLocal;
	@EJB
	private FlightServicesLocal flightServicesLocal;

	/**
	 * Default constructor.
	 */
	public PopulateDb() {
	}

	@PostConstruct
	public void initDb() {
		Passenger passenger = new Passenger("jridi", "nada", "F",
				DateParser.convertStringToDate("28/12/1991"), 2123,
				"nada@esprit.tn", "nada");
		Passenger passenger2 = new Passenger("Ben Mohamed", "Salah", "M",
				DateParser.convertStringToDate("05/01/1991"), 3214,
				"salah@esprit.tn", "salah");
		
		Passenger passenger3 = new Passenger("rezgui", "Ali", "M",
				DateParser.convertStringToDate("05/01/2015"), 3658,
				"Ali@esprit.tn", "Ali");

		Map<FlightCategory, Float> priceMap = new HashMap<>();
		priceMap.put(FlightCategory.ECONOMY, 100F);
		priceMap.put(FlightCategory.BUSINESS, 120F);
		priceMap.put(FlightCategory.VIP, 150F);

		Map<FlightCategory, Integer> freePlacesMap = new HashMap<FlightCategory, Integer>();
		freePlacesMap.put(FlightCategory.BUSINESS, 50);
		freePlacesMap.put(FlightCategory.ECONOMY, 100);
		freePlacesMap.put(FlightCategory.VIP, 10);

		Flight flight = new Flight(
				DateParser.convertStringToDate("04/12/2015"),
				DateParser.convertStringToDate("05/12/2015"), "France",
				"Tunisair", priceMap, freePlacesMap);

		passengerServicesLocal.addPassenger(passenger);
		passengerServicesLocal.addPassenger(passenger2);
		passengerServicesLocal.addPassenger(passenger3);

		flightServicesLocal.addFlight(flight);
	}
}
