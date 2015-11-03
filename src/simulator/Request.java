package simulator;

/**
 * Created by joshua on 11/2/15.
 */
public class Request
{
    private int arrival;
    private int track;
    private int sector;

    public Request(int arrival, int track, int sector)
    {
        this.arrival = arrival;
        this.track = track;
        this.sector = sector;
    }

    public int getArrival()
    {
        return arrival;
    }

    public int getTrack()
    {
        return track;
    }

    public int getSector()
    {
        return sector;
    }
}
