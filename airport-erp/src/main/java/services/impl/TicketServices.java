package services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.FlightServicesLocal;
import services.interfaces.PassengerServicesLocal;
import services.interfaces.TicketServicesLocal;
import services.interfaces.TicketServicesRemote;
import entities.Flight;
import entities.FlightCategory;
import entities.Passenger;
import entities.Ticket;
import entities.TicketID;

/**
 * Session Bean implementation class TicketServices
 */
@Stateless
public class TicketServices implements TicketServicesRemote,
		TicketServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private PassengerServicesLocal passengerServicesLocal;
	@EJB
	private FlightServicesLocal flightServicesLocal;
	@EJB
	private TicketServicesLocal ticketServicesLocal;

	/**
	 * Default constructor.
	 */
	public TicketServices() {
	}

	@Override
	public Boolean addTicket(Ticket ticket) {
		Boolean b = false;
		try {
			entityManager.persist(ticket);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean updateTicket(Ticket ticket) {
		Boolean b = false;
		try {
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deleteTicket(Ticket ticket) {
		Boolean b = false;
		Passenger passenger = ticket.getPassenger();
		Flight flight = ticket.getFlight();
		try {
			ticket = findTicketById(passenger.getId(), flight.getId());
			entityManager.remove(ticket);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Ticket findTicketById(Integer idPassenger, Integer idFlight) {
		Ticket ticket = new Ticket();
		return ticket;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTickets() {

		String jpql = "select t from t_ticket t";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTicketsByPassengerId(Integer passengerId) {
		String jpql = "select t from t_ticket t where t.idPassenger=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", passengerId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTicketsByFlightId(Integer flightId) {
		String jpql = "select t from t_ticket t where t.idFlight=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", flightId);
		return query.getResultList();
	}

	@Override
	public Boolean bookTicketBis(Integer idPassenger, Integer idFlight,
			FlightCategory flightCategory) {
		Boolean b = false;
		Passenger passenger = passengerServicesLocal
				.findPassengerById(idPassenger);
		Flight flight = flightServicesLocal.findFlightById(idFlight);

		if (flight.getFreePlacesMap().get(flightCategory) > 0) {
			Ticket ticket = new Ticket(passenger, flight, flightCategory);
			entityManager.persist(ticket);

			flight.getFreePlacesMap().put(flightCategory,
					flight.getFreePlacesMap().get(flightCategory) - 1);
			b = true;

		} else {
			System.out.println("not enough places ...");
		}

		return b;
	}

	@Override
	public Boolean bookTicket(Integer idPassenger, Integer idFlight,
			FlightCategory flightCategory) {
		Boolean b = false;
		try {
			Ticket ticket = new Ticket();
			TicketID ticketID = new TicketID(idPassenger, idFlight);
			Passenger passenger = passengerServicesLocal
					.findPassengerById(idPassenger);
			Flight flight = flightServicesLocal.findFlightById(idFlight);
			float price = (Float) null;
			ticket.setFlight(flight);
			ticket.setPassenger(passenger);
			ticket.setFlightCategory(flightCategory);
			ticket.setPrice(price);
			ticket.setTicketID(ticketID);
			entityManager.persist(ticket);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

}
