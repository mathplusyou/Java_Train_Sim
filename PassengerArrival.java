public class PassengerArrival
{
	int numPassengers, destinationFloor, timePeriod, expectedTimeOfArrival;

	public PassengerArrival(int numPassengers, int destinationFloor, int timePeriod) {
		//constructor
		this.numPassengers = numPassengers;
		this.destinationFloor = destinationFloor;
		this.timePeriod = timePeriod;
		this.expectedTimeOfArrival = timePeriod;
	}
	//getters
	public int getNumPassengers() {
		return numPassengers;
	}
	public int getDestinationFloor() {
		return destinationFloor;
	}
	public int getTimePeriod() {
		return timePeriod;
	}
	public int getExpectedTimeOfArrival(){
		return expectedTimeOfArrival;
	}
	//setters
	public void setNumPassengers(int num_passengers) {
		this.numPassengers = num_passengers;
	}
	public void setDestinationFloor(int station_destination) {
		destinationFloor = station_destination;
	}
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;
	}
	public void setExpectedTimeOfArrival(int expectedTimeOfArrival) {
		this.expectedTimeOfArrival = expectedTimeOfArrival;
	}

}
