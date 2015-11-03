package simulator;

/**
 * Created by joshua on 11/2/15.
 */
public class FirstComeFirstServe implements Algorithm
{

    @Override
    public Request run(Request[] requests)
    {
        Request next = requests[0];
        int time = next.getArrival();
        for(int k = 1; k < requests.length; k++)
        {
            if(requests[k] != null)
            {
                if(requests[k].getArrival() < time)
                {
                    next = requests[k];
                    time = next.getArrival();
                }
            }
        }

        return next;
    }
}
