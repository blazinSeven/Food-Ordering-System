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
    public int res_id;
    public String res_name;
    public String res_address;
    public float rate;

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
        if(restaurantName.equals(" ")|| restaurantName.equals(null))
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
                String searchQuery = "select * from restaurants where UPPER(restaurant_name) like ?";
                String Query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars from restaurants r, locations l, reviews rv WHERE r.user_id = l.user_id AND rv.restaurant_id = r.id  AND UPPER(restaurant_name) like ? GROUP BY r.id,r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars ORDER BY r.waiting_time";
                PreparedStatement ppstmt = connection.coon.prepareStatement(Query);
                ppstmt.setString(1,restaurantName);
                ResultSet rs = ppstmt.executeQuery();
                if (rs.next())
                {
                    /**
                    restaurantFound="Restaurant Name: "+rs.getString("restaurant_name") + "\n"+
                                    "Restaurant Open Time: "+ rs.getString("open_time") +"\n" +
                                    "Restaurant Close Time: " + rs.getString("close_time")+ "\n"+
                                    "Restaurant Phone Number: " + rs.getString("phone_num")+ "\n" +
                                    "Restaurant E-Mail: " + rs.getString("e_mail_address");
                    restaurant_id =rs.getInt("id");
                     **/
                    res_id = rs.getInt(1);
                    res_name = rs.getString(2);
                    res_address = rs.getInt(3)+" "+
                            rs.getString(4)+" "+
                            rs.getString(5)+ " "+
                            rs.getString(6);
                    rate = rs.getFloat(7);
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
        String k = "kfc";
        sr.SearchRestaurant(k.toUpperCase());
        if (sr.message.equals("Found")){
            System.out.println(sr.res_id);
            System.out.println(sr.res_name);
            System.out.println(sr.res_address);
            System.out.println(sr.rate);
        }
        else {
            System.out.println("nooo");
        }


    }
}
