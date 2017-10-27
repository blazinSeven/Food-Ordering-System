package google;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import sun.misc.FloatingDecimal;

import java.util.Scanner;

public class GetDistance {

    /**
     * Context for connecting google api key
     */
    GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCbZwJ0Y8Qw01YObRvoNXCasw2mLimHMZw").build();
    String getDistance = null;
    Float distanceFloat;

    /**
     * When Call this function, you will get the distance between origin and destination
     * @param origin
     * @param destination
     */
    public void getDistance(String origin, String destination) {

        if (context != null) {
            System.out.println("Connect");
            String [] orig = {origin};
            String [] dest = {destination};
            DirectionsResult result = DirectionsApi.getDirections(context, orig[0], dest[0]).awaitIgnoreError();
            Distance distance = new Distance();
            distance.getClass().getResource(result.routes.clone().toString());
            DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context, orig, dest).awaitIgnoreError();
            getDistance = distanceMatrix.rows[0].elements[0].distance.humanReadable;
            //distanceFloat = Float.valueOf(distanceMatrix.rows[0].elements[0].distance.humanReadable.toString());


        }
    }

    /**
     * getFloat function that use for change string distance that get from google to float
     * @param s
     * @return
     */
    float getFloat(String s)
    {
        String result="";
        for(int c = 0; c< s.length(); c++)
        {
            Character character = s.charAt(c);
            if(Character.isDigit(character))
            {
                result += character;
            }
        }
        float f = Float.valueOf(result);
        return f;

    }


    public static void main (String arg[])
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the origin address:  \n");
        String origin = in.nextLine();
        System.out.println("Please enter the final destination:  \n");
        String destination = in.nextLine();
        GetDistance getDistance = new GetDistance();
        getDistance.getDistance(origin,destination);
        //Test with string
        System.out.println(getDistance.getDistance);
<<<<<<< HEAD
        Float getDistanceNum = Float.valueOf(getDistance.getDistance);
=======
        //Float getDistanceNum = Float.valueOf(getDistance.getDistance);
>>>>>>> 789c8c0e60eb3273bdde6cbd0337bfe66700b092
        //Test with float
        System.out.println(getDistance.getFloat(getDistance.getDistance));
        in.close();
    }
}
