import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainSimulation {
	int simLength;
	int rateSimSec;
	private ArrayList<ArrayList<PassengerArrival>> PassArrivalBeh;		
	private static Scanner xfile;
	private Train[] trains;
	private Thread[] threads;
	private TrainSystemManager TSM;
	
	public TrainSimulation() {
		//constructor
		this.simLength = 0;
		this.rateSimSec = 0;
		this.PassArrivalBeh = new ArrayList<ArrayList<PassengerArrival>>();
		this.threads = new Thread[5];
        this.trains = new Train[5];
        TSM=new TrainSystemManager();
        for (int i = 0; i < 5; i++){
            trains[i] = new Train(i, TSM);
            threads[i] = new Thread(trains[i]);
		}
	}
	private void openFile() {
		//opens file and inputs information into passenger objects and arrayLists
		try{	
			xfile = new Scanner(new File("TrainConfig.txt"));
			simLength = Integer.parseInt(xfile.nextLine());
			rateSimSec = Integer.parseInt(xfile.nextLine());
			for (int i=0; i < 5; i++){
				String[] thisLine;
				ArrayList<PassengerArrival> PassArrivalObjects = new ArrayList<PassengerArrival>();
				thisLine = new String[9];
				thisLine = xfile.nextLine().split(";| ");
				PassArrivalObjects.add(new PassengerArrival(Integer.parseInt(thisLine[0]), Integer.parseInt(thisLine[1]), Integer.parseInt(thisLine[2])));
				
				if (thisLine.length >= 6) {
					PassArrivalObjects.add(new PassengerArrival(Integer.parseInt(thisLine[3]), Integer.parseInt(thisLine[4]), Integer.parseInt(thisLine[5])));
				}
				if (thisLine.length >= 9) {
					PassArrivalObjects.add(new PassengerArrival(Integer.parseInt(thisLine[6]), Integer.parseInt(thisLine[7]), Integer.parseInt(thisLine[8])));
				}
				PassArrivalBeh.add(PassArrivalObjects);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			xfile.close();
		}
	}

	void start() {
		//starts the threads and 
		openFile();
		for (int i=0; i<5; i++){
			this.threads[i].start();	
		}
		while ( SimClock.getTime() <= simLength) {
			for (int i=0; i<5; i++)
			{
				for (int m=0; m<PassArrivalBeh.get(i).size(); m++)
				{
					int spawnTime = PassArrivalBeh.get(i).get(m).getTimePeriod();
//					System.out.println(spawnTime);
					if (PassArrivalBeh.get(i).get(m).getExpectedTimeOfArrival() == SimClock.getTime()) 
					{
						TrainSystemManager.setArrival(i, PassArrivalBeh.get(i).get(m).getNumPassengers(), PassArrivalBeh.get(i).get(m).getDestinationFloor());
						PassArrivalBeh.get(i).get(m).setExpectedTimeOfArrival(PassArrivalBeh.get(i).get(m).getExpectedTimeOfArrival()+ spawnTime);
//						System.out.println(PassArrivalBeh.get(i).get(m).getExpectedTimeOfArrival());
					}
//					if( TSM.getStation(i).getPassengerRequests(m) > 0)
//					{
//						
//					}
				}
			}
			
			try
			//puts threads to sleep
			{
				Thread.sleep(rateSimSec);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//increments time
			SimClock.tick();	
		}
		//interrupts threads
		for (int i = 0; i < 5; i++){
            threads[i].interrupt();
		}
        printBuildingState();    
		
	}

	void printBuildingState() {
	//Prints state of the trains and building floors in Simulation State
		for (int x = 0 ; x < 5 ; x++ ){
			TrainStation station = TSM.getStation(x);
			System.out.println("**************");
			System.out.println("Station " + x + " Statistics: " + "\n");
			System.out.println("Total Destination Requests from Station " + x);
			for (int i = 0; i < 5; i++ ){
				System.out.println(station.getTotalDestinationRequests(i) + " requests to go to Station " + i);
			}
			System.out.println("\n" + "Total Arrived Passengers: ");
			
			for (int k = 0; k < 5; k++){
				System.out.println(station.getArrivedPassengers(k)+ " arrived at station " + k);
			}
			System.out.println("");
			
		}
		for (int n = 0 ; n < 5 ; n++ ){
			System.out.println("**************");
			System.out.println("Train " + n + " Statistics: ");
			System.out.print("Total Passengers Entered: ");
			System.out.print(trains[n].getTotalLoadedPassengers() + "\n");
			System.out.print("Total Passengers Exited: ");
			System.out.print(trains[n].getTotalUnloadedPassengers() + "\n");
			System.out.println("");
			}
		}
	
}


