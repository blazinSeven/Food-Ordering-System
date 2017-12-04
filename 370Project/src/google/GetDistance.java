package google;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import sun.misc.FloatingDecimal;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Yinsheng Dong(yid164)
 * This class is using Google API to get distance between 2 positions
 **/
public class GetDistance {

    /**
     * Context for connecting google api key
     */
    GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCbZwJ0Y8Qw01YObRvoNXCasw2mLimHMZw").build();
    String getDistance = null;
    Float distanceFloat;

    /**
     * When Call this function, you will get the String of distance between origin and destination
     * @param origin
     * @param destination
     * @return a string
     */
    public String getDistanceString(String origin, String destination) {

        if (context != null) {
            //System.out.println("Connect");
            String [] orig = {origin};
            String [] dest = {destination};
            System.out.println(orig[0]);
            System.out.println(dest[0]);
            DirectionsResult result = DirectionsApi.getDirections(context, orig[0], dest[0]).awaitIgnoreError();
            Distance distance = new Distance();
            distance.getClass().getResource(Arrays.toString(result.routes.clone()));
            DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context, orig, dest).awaitIgnoreError();
            getDistance = distanceMatrix.rows[0].elements[0].distance.humanReadable;
            //distanceFloat = Float.valueOf(distanceMatrix.rows[0].elements[0].distance.humanReadable.toString());
        }
        return getDistance;

    }

    /**
     * getFloatDistance function so will get the float of distance between origin and destination
     * @param origin, destination
     * @return a float
     */
    public float getFloatDistance(String origin, String destination)
    {
        String result="";
        String toConvert = getDistanceString(origin,destination);
        for(int c = 0; c< toConvert.length(); c++)
        {
            Character character = toConvert.charAt(c);
            if(!Character.isLetter(character))
            {
                result += character;
            }
        }
        distanceFloat = Float.valueOf(result);
        return distanceFloat;
    }


    /**
     * Main function for testing
     **/
    public static void main (String arg[])
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the origin address:  \n");
        String origin = in.nextLine();
        System.out.println("Please enter the final destination:  \n");
        String destination = in.nextLine();
        GetDistance getDistance = new GetDistance();;
        getDistance.getDistanceString(origin,destination);
        System.out.println(getDistance.toString());
        getDistance.getFloatDistance(origin,destination);
        System.out.println(getDistance.toString());
        //Test with string
        //System.out.println(getDistance.getDistance);
        //Float getDistanceNum = Float.valueOf(getDistance.getDistance);
        //Test with float
        //System.out.println(getDistance.getFloatDistance(origin,destination));
        //System.out.println(getDistance.getDistanceString(origin,destination));
        in.close();
    }
}
