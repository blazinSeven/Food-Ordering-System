package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kocur on 2017-10-25.
 */
public class SearchDishes {
    GoConnection connection = new GoConnection();
    public String message = null;
    public String dishFound = null;
    public int k;


    /**
     * @param search -- string to search
     *               Queries the dishes table for a dish with the name "matching" the string passed
     * @precond search string not null
     * @postcond none
     */
    public void SearchDishes(String search) {
        connection.connect();
        //Check for a blank string
        if (search.equals(" ") || search.equals(null)) {
            message = "Error: Can't search for a blank string";
            try {
                connection.coon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        //When the connection is live, and the arg passed isn't null
        else if (connection.coon != null) {
            try {
                String searchQuery = "SELECT * FROM dishes where dish_name like ?";
                PreparedStatement ppstmt = connection.coon.prepareStatement(searchQuery);
                ppstmt.setString(1, search);
                ResultSet rs = ppstmt.executeQuery();
                if (rs.next()) {
                    //Set the string to the desired values from the database
                    dishFound = "Dish Name: " + rs.getString("dish_name") + "\n" +
                            "Dish Price:" + rs.getString("dish_prices") + "\n" +
                            "Dish Preperation Time:" + rs.getString("dish_preperation_time");
                    k = rs.getInt("id");
                    message = "Found";
                    connection.coon.close();
                //If the connection isn't working
                } else {
                    message = "Not found";
                    connection.coon.close();
                }
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        SearchDishes searchDish = new SearchDishes();
        searchDish.SearchDishes("Fries");

    }



}
