package entity;

public class EconomyClassSeat extends Seat{
	
	private double seatPrice;
	private String seatName;
	
	public EconomyClassSeat(int seatNumber, double seatPrice, int flightId) {
		super(seatNumber, flightId);
		this.seatPrice=seatPrice;
		this.seatName = "Economy Class Seat " + seatNumber;
	}

	public EconomyClassSeat(int seatNumber, int flightId) {
		super(seatNumber, flightId);
		this.seatPrice=5000;
		this.seatName = "Economy Class Seat " + seatNumber;
	}

	public double getSeatPrice() {
		return seatPrice;
	}
	
	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
}
	

