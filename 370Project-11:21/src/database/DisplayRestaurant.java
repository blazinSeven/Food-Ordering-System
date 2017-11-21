package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Written by Yinsheng Dong (yid164)
 * This is just a helper class to check every restaurant in the sql database
 */
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


    public void displayRestaurantFromID(int restID)
    {
        connect.connect();
        if (connect.coon != null) {
            String selectQuery = "SELECT * FROM restaurants WHERE id = ? ";
            try {
                PreparedStatement ppstmt = connect.coon.prepareStatement(selectQuery);
                ppstmt.setInt(1,restID);
                ResultSet results = ppstmt.executeQuery();
                if(results.next())
                {
                    System.out.print(results.getString("restaurant_name" ));
                }
                restaurantDisplay = new ArrayList<String>();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    //Helper
    private void queryForRestaurantName(int restID)
    {
        connect.connect();
        if (connect.coon != null) {
            String selectQuery = "SELECT restaurant_name FROM restaurants WHERE id = ? ";
            try {
                PreparedStatement ppstmt = connect.coon.prepareStatement(selectQuery);
                ppstmt.setInt(1,restID);
                ResultSet results = ppstmt.executeQuery();
                if(results.next())
                {
                     System.out.print(results.getString("restaurant_name" ));
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    public String getRestaurantName(int restID)
    {
        Scanner inScanner = new Scanner(System.in);
        String restName = inScanner.nextLine();
        queryForRestaurantName(restID);
        return restName;
    }



    public static void main(String arg[])
    {
        DisplayRestaurant restaurant = new DisplayRestaurant();
     //   restaurant.displayRestaurant();
     //   System.out.println(restaurant.restaurantDisplay);

       String testRest = restaurant.getRestaurantName(1);
       System.out.print(testRest);
       // restaurant.displayRestaurantFromID(1);
    }
}
