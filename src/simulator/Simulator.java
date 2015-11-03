package simulator;

/**
 * Created by joshua on 11/2/15.
 */
public class Simulator
{
    public static void main(String[] args)
    {
        Algorithm algorithm = new FirstComeFirstServe();
        Request[] requests = createTestData();

        int track = 0;
        int sector = 0;
        int time = 0;
        int requestsLeft = requests.length;

        while(requestsLeft > 0)
        {
            Request next = algorithm.run(requests);
        }
    }

    private static Request[] createTestData()
    {
        Request[] toReturn = new Request[10];
        toReturn[0] = new Request(0, 54, 0);
        toReturn[1]= new Request(23, 132, 6);
        toReturn[2] = new Request(26, 29, 2);
        toReturn[3] = new Request(29, 23, 1);
        toReturn[4] = new Request(35, 198, 7);
        toReturn[5] = new Request(45, 170, 5);
        toReturn[6] = new Request(57, 180, 3);
        toReturn[7] = new Request(83, 78, 4);
        toReturn[8] = new Request(88, 73, 5);
        toReturn[9] = new Request(95, 249, 5);

        return toReturn;
    }
}
