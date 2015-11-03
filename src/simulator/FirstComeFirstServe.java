package simulator;

/**
 * Created by joshua on 11/2/15.
 */
public class FirstComeFirstServe implements Algorithm
{

    @Override
    public int[] run(Request[] requests, double time)
    {
        Request next = null;
        int nearestTime = 9999999;
        for(int k = 0; k < requests.length; k++)
        {
            if(requests[k] != null && requests[k].getArrival() <= time)
            {
                if(requests[k].getArrival() < nearestTime)
                {
                    next = requests[k];
                    nearestTime = next.getArrival();
                }
            }
        }

        if(next != null)
        {
            return new int[]{next.getTrack(), next.getSector()};
        }
        else
        {
            return null;
        }
    }
}
