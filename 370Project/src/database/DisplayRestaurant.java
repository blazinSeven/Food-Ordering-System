package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DisplayRestaurant {

    public ArrayList restaurantDisplay = null;
    GoConnection connect = new GoConnection();

    public void displayRestaurant()
    {
        connect.connect();
        if(connect.coon != null)
        {
            String selectQuery = "select * from restaurants";
            try {
                Statement stmt = connect.coon.createStatement();
                ResultSet results = stmt.executeQuery(selectQuery);
                restaurantDisplay = new ArrayList<String>();
                int rowcount = 0;
                while (results.next())
                {
                    restaurantDisplay.add(results.getString("restaurant_name"));
                    rowcount ++;
                }
                System.out.println("Total restaurant: "+rowcount);

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String arg[])
    {
        DisplayRestaurant restaurant = new DisplayRestaurant();
        restaurant.displayRestaurant();
        System.out.println(restaurant.restaurantDisplay);
    }
}
