public class SimClock
{
	static int SimTime;
	
	SimClock()
	//constructor
	{
		SimTime = 0;
	}
	public static void tick()
	//increments time
	{
		SimTime++;
	}
	public static int getTime()
	//returns time
	{
		return SimTime;
	}
	
}
