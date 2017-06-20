import java.util.ArrayList;

public class Train implements Runnable {
	int trainID = 0;
	int currentTrainStation = 0;
	int numPassengers = 0;
	int totalLoadedPassengers = 0;
	int totalUnloadedPassengers = 0;
	ArrayList<TrainEvent> moveQueue;
	int[] passengerDestinations;
	TrainSystemManager TSM;
	int pastTime =0;
	
	public Train( int trainID, TrainSystemManager TSM) {
		//constructor
		this.trainID = trainID;
		this.TSM = TSM;
		passengerDestinations = new int[5];
		this.moveQueue = new ArrayList<TrainEvent>();
	}
	public void run(){
		//implements train logic
		while (!Thread.currentThread().isInterrupted() ){
			if (moveQueue.size() == 0){
				int destinationStation = TSM.checkStation(trainID);
				if (destinationStation != -1){
					TrainEvent newEvent = new TrainEvent(destinationStation, SimClock.getTime() + Math.abs(destinationStation - currentTrainStation) * 5 + 10);
					moveQueue.add(newEvent);
					for (int x = 0; x < passengerDestinations.length; x++){
						passengerDestinations[x] = TSM.getStation(destinationStation).getPassengerRequests(x);
					}
				}
			}
			//processes the event
			if (moveQueue.size() > 0){
				if (SimClock.getTime() == moveQueue.get(0).getExpectedArrival()){
					currentTrainStation = moveQueue.get(0).getDestination();
					pastTime =  moveQueue.get(0).getExpectedArrival();
					if (numPassengers == 0){
						//loading passengers
						moveQueue.remove(0);
						for (int i = currentTrainStation; i < 5; i++){
			                int numPass = TSM.getStation(currentTrainStation).getPassengerRequests(i);
			                if (numPass > 0){
			                    totalLoadedPassengers += numPass;
			                    TSM.getStation(currentTrainStation).setTotalDestinationRequests(currentTrainStation, numPass);
			                    numPassengers += numPass;
			                    System.out.println(SimClock.getTime() + ": Train " + trainID + " loaded " + numPassengers + " at Station " + currentTrainStation );
			                    moveQueue.add(new TrainEvent(i, SimClock.getTime() + pastTime + Math.abs(currentTrainStation - i) * 5 + 10));
			                    TSM.getStation(currentTrainStation).setPassengerRequests(currentTrainStation,0);				              
			                    
			                }
			            }

						TSM.getStation(currentTrainStation).setApproachingTrain(-1);
			        }
				}
					if (numPassengers > 0){
						//unloading passengers
						System.out.println(SimClock.getTime() + ": Train " +  trainID + " unloaded "+ numPassengers + " passengers at Station " + currentTrainStation);
						numPassengers -= passengerDestinations[currentTrainStation];
						TSM.getStation(currentTrainStation).setArrivedPassengers(currentTrainStation, passengerDestinations[currentTrainStation]);
						totalUnloadedPassengers += numPassengers;
						passengerDestinations[currentTrainStation] = 0;
						moveQueue.remove(0);
						}
					}
				}
			}
	public int getTotalLoadedPassengers(){
		return totalLoadedPassengers;
		
	}
	public int getTotalUnloadedPassengers(){
		return totalUnloadedPassengers;
		
	}
	}
	
	
