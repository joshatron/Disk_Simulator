package simulator;

import java.util.Random;

/**
 * Created by joshua on 11/2/15.
 */
public class Simulator
{

    public static void main(String[] args)
    {
        Algorithm algorithm = new C_Look();
        Request[] requests = createRandomData();
        double[] results = new double[requests.length];

        int track = 0;
        int sector = 0;
        double time = 0;
        int requestsLeft = requests.length;

        while(requestsLeft > 0)
        {
            int[] location = algorithm.run(requests, time);
            if(location != null)
            {
                time += moveToLocation(location[0], location[1], track, sector);
                track = location[0];
                sector = location[1];
                for(int k = 0; k < requests.length; k++)
                {
                    if(requests[k] != null)
                    {
                        if(requests[k].getTrack() == track && requests[k].getSector() == sector)
                        {
                            results[k] = time - requests[k].getArrival();
                            requests[k] = null;
                            requestsLeft--;
                        }
                    }
                }
            }
            else
            {
                time += .1;
            }
        }

        printResults(results);
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

    private static Request[] createRandomData()
    {
        int num = 50;
        int max_time = 100;
        int max_track = 250;
        int max_sector = 8;

        Random rand = new Random(1);

        Request[] requests = new Request[num];

        for(int k = 0; k < num; k++)
        {
            requests[k] = new Request(rand.nextInt(max_time), rand.nextInt(max_track), rand.nextInt(max_sector));
        }

        Request[] toReturn = new Request[num];
        for(int k = 0; k < num; k++)
        {
            Request minRequest = new Request(max_time + 1, 0, 0);
            int minIndex = 0;
            for(int a = 0; a < num; a++)
            {
                if(requests[a] != null)
                {
                    if(requests[a].getArrival() < minRequest.getArrival())
                    {
                        minRequest = requests[a];
                        minIndex = a;
                    }
                }
            }
            toReturn[k] = minRequest;
            requests[minIndex] = null;
        }

        return toReturn;
    }

    private static double moveToLocation(int track, int sector, int oldTrack, int oldSector)
    {
        double time = 0;
        int trackMove = Math.abs(oldTrack - track);
        time += 10 + .1 * trackMove;

        int sectorMove = 0;
        if(sector < oldSector)
        {
            sectorMove += 8 - oldSector;
            sectorMove += sector;
        }
        else
        {
            sectorMove = sector - oldSector;
        }
        time += sectorMove;

        return time;
    }

    private static void printResults(double[] results)
    {
        double average = 0;
        for(int k = 0; k < results.length; k++)
        {
            average += results[k];
        }
        average /= results.length;

        double stdDev = 0;
        for(int k = 0; k < results.length; k++)
        {
            stdDev += Math.pow(results[k] - average, 2);
        }
        stdDev /= results.length;
        double variance = stdDev;
        stdDev = Math.sqrt(stdDev);

        System.out.println("average:            " + average);
        System.out.println("variance:           " + variance);
        System.out.println("standard deviation: " + stdDev);
    }
}
