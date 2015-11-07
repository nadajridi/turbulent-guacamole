package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.FlightCategory;
import entities.Ticket;

@Remote
public interface TicketServicesRemote {

	Boolean addTicket(Ticket ticket);

	Boolean updateTicket(Ticket ticket);

	Boolean deleteTicket(Ticket ticket);

	Ticket findTicketById(Integer idPassenger, Integer idFlight);

	List<Ticket> findAllTickets();

	List<Ticket> findAllTicketsByPassengerId(Integer passengerId);

	List<Ticket> findAllTicketsByFlightId(Integer flightId);

	Boolean bookTicket(Integer idPassenger, Integer idFlight,
			FlightCategory flightCategory);

	Boolean bookTicketBis(Integer idPassenger, Integer idFlight,
			FlightCategory flightCategory);

}
