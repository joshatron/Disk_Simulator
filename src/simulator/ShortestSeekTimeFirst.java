package simulator;

/**
 * Created by joshua on 11/3/15.
 */
public class ShortestSeekTimeFirst implements Algorithm
{
    int track = 0;
    int sector = 0;

    @Override
    public int[] run(Request[] requests, double time)
    {
        int minDistance = 999;
        Request nearest = null;
        for(int k = 0; k < requests.length; k++)
        {
            if(requests[k] != null && requests[k].getArrival() <= time)
            {
                int distance = Math.abs(track - requests[k].getTrack()) + Math.abs(sector - requests[k].getSector());
                if(distance < minDistance)
                {
                    minDistance = distance;
                    nearest = requests[k];
                }
            }
        }

        if(nearest != null)
        {
            track = nearest.getTrack();
            sector = nearest.getSector();

            return new int[]{nearest.getTrack(), nearest.getSector()};
        }
        else
        {
            return null;
        }
    }
}
