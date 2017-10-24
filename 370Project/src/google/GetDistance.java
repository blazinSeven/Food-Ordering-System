package google;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;

import java.util.Scanner;

public class GetDistance {

    /**
     * Context for connecting google api key
     */
    GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCbZwJ0Y8Qw01YObRvoNXCasw2mLimHMZw").build();
    String getDistance = null;

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


        }
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
        System.out.println(getDistance.getDistance);
        in.close();
    }
}