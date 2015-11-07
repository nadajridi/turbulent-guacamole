package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
@Table(name="t_car")

public class Car implements Serializable {

	
	private Integer id;
	private String registrationNumber;
	private String brand;
	private CarCategory category;
	@Enumerated(EnumType.STRING)
	private CarState state;
	private String description;
	private Date startDate;
	private Date endDate;
	private float price;
	@ManyToMany
	private List<Passenger> passengers;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private CarRentalAgency agency;

	public Car() {
		super();
	}   
	
	
	
	public Car(String registrationNumber, String brand, CarCategory category,
			CarState state,
			String description, Date startDate, Date endDate, float price) {
		super();
		this.registrationNumber = registrationNumber;
		this.brand = brand;
		this.category = category;
		this.state = state;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		
	}



	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}   
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}   
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}   
	
	
	public CarCategory getCategory() {
		return category;
	}
	
	@ManyToMany 
	@JoinColumn(name = "idPassenger", referencedColumnName = "id", updatable = false, insertable = false)
	
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	public void linkPassengersToThisCar(List<Passenger> passengers) {
		this.passengers = passengers;
		for (Passenger p : passengers) {
			p.setCars(this);
		}
	}



	public void setCategory(CarCategory category) {
		this.category = category;
	}
	

	public CarState getState() {
		return state;
	}



	public void setState(CarState state) {
		this.state = state;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToOne
	@JoinColumn(name = "idCarRentalAgency", referencedColumnName = "id", updatable = false, insertable = false)
	public CarRentalAgency getAgency() {
		return agency;
	}



	public void setAgency(CarRentalAgency agency) {
		this.agency = agency;
	}



	
   
}
