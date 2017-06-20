public class TrainStation
{
	private int[] totalDestinationRequests; 
	private int[] passengerRequests;
	private  int[] arrivedPassengers; 

	private int approachingTrain;
	
	public TrainStation(){
		//constructor
		totalDestinationRequests = new int[5];
		passengerRequests = new int[5];
		arrivedPassengers = new int[5];
		
		for(int i=0;i<5;i++)
		//initializes arrays
		{
			totalDestinationRequests[i]=0;
			passengerRequests[i]=0;
			arrivedPassengers[i]=0;	
		}
		approachingTrain = -1;
				
	}
	//getters
	public int getTotalDestinationRequests(int index) {
		return totalDestinationRequests[index];
	}
	public int getArrivedPassengers(int index) {
		return arrivedPassengers[index];
	}
	public int getPassengerRequests(int index) {
		return passengerRequests[index];
	}
	public int getApproachingStation() {
		return approachingTrain;
	}
	//setters
	public void setTotalDestinationRequests(int station, int passengers) {
		totalDestinationRequests[station] += passengers;
	}
	public void setArrivedPassengers(int station, int passengers) {
		arrivedPassengers[station] += passengers;
	}
	public synchronized void setPassengerRequests(int station, int passengers) {
		//Took synchronized off
		if (passengers != 0) {
		passengerRequests[station] = passengers;}
		if (passengers == 0) {
			passengerRequests[station] = 0;}
	}
	public synchronized void setApproachingTrain(int approachingT) {
		//Took synchronized off
		approachingTrain = approachingT;
	}
}
