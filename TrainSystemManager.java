public class TrainSystemManager {
	 static TrainStation [] stations;
	
	public TrainSystemManager() {
		//constructor
		stations = new TrainStation[5];
		
		for(int i=0;i<5;i++)
			stations[i]=new TrainStation();
	}
	
	//setters
	public static void setArrival(int CurrentFloor, int numPassengers, int DestinationFloor)
	{
		stations[DestinationFloor].setTotalDestinationRequests(CurrentFloor, numPassengers);
		stations[DestinationFloor].setPassengerRequests(CurrentFloor, numPassengers);
//		floors[CurrentFloor].setArrivedPassengers(DestinationFloor, numPassengers);
	}
	//getters
	public TrainStation getStation(int stationNumber) {
		return stations[stationNumber];
	}
	//checks floor for passengers waiting
	public synchronized int checkStation(int trainID){
		for (int i = 0; i<5; i++ ){
			for (int m =0; m<5; m++){
				if(stations[i].getPassengerRequests(m) !=0 && stations[i].getApproachingStation() == -1){
					stations[i].setApproachingTrain(trainID);
					//Switched order just to make sense. Passenger first spawn, then set to approach station
					System.out.println(SimClock.getTime() + ": "+ stations[i].getPassengerRequests(m) + " passengers spawn on station " + m);

					System.out.println(SimClock.getTime() + ": Train " + trainID + " set to approach station " + m + " to drop off at station " + i );
					return m;
				}
			}
		}
		return -1;
	}
}

