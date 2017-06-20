public class TrainEvent {
	int destination = 0;
	int expectedArrival = 0;
	
	public TrainEvent(int destination, int expectedArrival){
		//constructor
		this.destination = destination;
		this.expectedArrival = expectedArrival;
	}
	//getters
	public int getDestination() {
		return destination;
	}
	public int getExpectedArrival() {
		return expectedArrival;
	}
	//setters
	public void setExpectedArrival(int newExpectedArrival){
		this.expectedArrival = newExpectedArrival;
	}
}
