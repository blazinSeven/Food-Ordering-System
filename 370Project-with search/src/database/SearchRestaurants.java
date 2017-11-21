package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Written by Yinsheng Dong(yid164)
 * This class is using to search a restaurant by name
 */
public class SearchRestaurants {

    /**
     * connection first
     */
    GoConnection connection = new GoConnection();

    /**
     * public message
     */
    public String message = null;

    /**
     * What the function found
     */
    public String restaurantFound = null;

    /**
     * the restaurant_id that searched
     */
    public int restaurant_id;

    /**
     * the function to search a restaurant by a name that given by users
     * @param restaurantName
     * @pre-cond: the search cannot be null or empty
     */
    public void SearchRestaurant(String restaurantName)
    {
        // make sure connection first
        connection.connect();

        // the search cannot by null or empty set
        if(restaurantName.equals("")|| restaurantName.equals(null))
        {
            message = "Can not search the empty input";
            try{
                connection.coon.close();
            }catch (SQLException e)
            {
                e.getStackTrace();
            }
        }
        else if (connection.coon!=null){
            try{
                // use the search query to search the restaurant information in the db, then return the message out
                // the SQL Query that will be executed
                String searchQuery = "select * from restaurants where restaurant_name like ?";
                // What will be returned
                PreparedStatement ppstmt = connection.coon.prepareStatement(searchQuery);
                // The first question mark
                ppstmt.setString(1,restaurantName);
                // execute the query. rs = what is returned
                ResultSet rs = ppstmt.executeQuery();
                // should be "while"
                if (rs.next())
                {
                    restaurantFound="Restaurant Name: "+rs.getString("restaurant_name") + "\n"+
                                    "Restaurant Open Time: "+ rs.getString("open_time") +"\n" +
                                    "Restaurant Close Time: " + rs.getString("close_time")+ "\n"+
                                    "Restaurant Phone Number: " + rs.getString("phone_num")+ "\n" +
                                    "Restaurant E-Mail: " + rs.getString("e_mail_address");
                    restaurant_id =rs.getInt("id");
                    message = "Found";
                    connection.coon.close();
                }
                else
                {
                    message = "Not found";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                e.getStackTrace();
            }
        }
    }

    /**
     * main function for testing
     * @param args
     */
    public static void main(String[] args)
    {
        SearchRestaurants sr = new SearchRestaurants();
        sr.SearchRestaurant("KFC");
        System.out.println(sr.restaurantFound);
    }
}
