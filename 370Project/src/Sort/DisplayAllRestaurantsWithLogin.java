package Sort;

import database.GoConnection;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class DisplayAllRestaurantsWithLogin {

    private ArrayList<SortInfo> sortInfoes;
    public String message = "";
    private float distance = 0;
    private int rest_id = 0;
    private String rest_name = "";
    private String rest_address = "";
    private Time rest_time = null;
    private float rest_rate = 0;
    private float rest_avg_price = 0;

    public ArrayList<SortInfo> getAllRestaurants() {
        return sortInfoes;
    }

    private void displayAllRestaurant(int customer_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
        }
    }
}
