package simulator;

/**
 * Created by joshua on 11/3/15.
 */
public class Look implements Algorithm
{
    int track = 0;
    int sector = 0;
    boolean dir = true;

    @Override
    public int[] run(Request[] requests, double time)
    {
        int nearest;
        Request next = null;
        if(dir)
        {
            nearest = 250;
        }
        else
        {
            nearest = -1;
        }

        for(int k = 0; k < requests.length; k++)
        {
            if(requests[k] != null && requests[k].getArrival() <= time)
            {
                if(dir)
                {
                    if(requests[k].getTrack() < nearest && requests[k].getTrack() >= track)
                    {
                        next = requests[k];
                        nearest = next.getTrack();
                    }
                }
                else
                {
                    if(requests[k].getTrack() > nearest && requests[k].getTrack() <= track)
                    {
                        next = requests[k];
                        nearest = next.getTrack();
                    }
                }
            }
        }

        if(next == null)
        {
            dir = !dir;
            if(dir)
            {
                nearest = 250;
            }
            else
            {
                nearest = -1;
            }
            for(int k = 0; k < requests.length; k++)
            {
                if(requests[k] != null && requests[k].getArrival() <= time)
                {
                    if(dir)
                    {
                        if(requests[k].getTrack() < nearest && requests[k].getTrack() >= track)
                        {
                            next = requests[k];
                            nearest = next.getTrack();
                        }
                    }
                    else
                    {
                        if(requests[k].getTrack() > nearest && requests[k].getTrack() <= track)
                        {
                            next = requests[k];
                            nearest = next.getTrack();
                        }
                    }
                }
            }
        }

        if(next != null)
        {
            track = next.getTrack();
            sector = next.getSector();

            return new int[]{next.getTrack(), next.getSector()};
        }
        else
        {
            return null;
        }
    }
}
