package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Kocur on 2017-10-24.
 * Default creator, only need to use display method.
 */
public class DisplayDishes {

    private ArrayList dishesDisplay = null;
    GoConnection connection = new GoConnection();


    /**
       In: Nothing
       Out:  Nothing is returned, dishes in the database are printed to the console.
        The function queries the sql database for the name of all food in the database.
     */
    public void displayDishes() {
        connection.connect();
        if (connection.coon != null) {
            String selectQuery = "select * from dishes";
            try {
                Statement stmt = connection.coon.createStatement();
                ResultSet results = stmt.executeQuery(selectQuery);
                dishesDisplay = new ArrayList<String>();
                int rowCount = 0;
                while (results.next()) {
                    dishesDisplay.add(results.getString("dish_name"));
                    rowCount++;
                }
                System.out.println("Total Dishes: " + rowCount);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("Error: A connection could not be established.  ");
        }
    }


    //Main for testing
    public static void main(String[] args)
    {

        DisplayDishes testDisplay = new DisplayDishes();

        //Print to console all food in the database
        testDisplay.displayDishes();
    }




}
