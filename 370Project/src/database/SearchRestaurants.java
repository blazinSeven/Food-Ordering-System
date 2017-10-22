package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchRestaurants {
    GoConnection connection = new GoConnection();
    public String message = null;
    public String restaurantFound = null;

    public void SearchRestaurant(String search)
    {
        connection.connect();
        if(search.equals(" ")|| search.equals(null))
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
                String searchQuery = "select * from restaurants where restaurant_name like ?";
                PreparedStatement ppstmt = connection.coon.prepareStatement(searchQuery);
                ppstmt.setString(1,search);
                ResultSet rs = ppstmt.executeQuery();
                if (rs.next())
                {
                    restaurantFound="Restaurant Name: "+rs.getString("restaurant_name") + "\n"+
                                    "Restaurant Open Time: "+ rs.getString("open_time") +"\n" +
                                    "Restaurant Close Time: " + rs.getString("close_time")+ "\n"+
                                    "Restaurant Phone Number: " + rs.getString("phone_num")+ "\n" +
                                    "Restaurant E-Mail: " + rs.getString("e_mail_address");
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

    public static void main(String[] args)
    {
        SearchRestaurants sr = new SearchRestaurants();
        sr.SearchRestaurant("KFC");
        System.out.println(sr.restaurantFound);
    }
}
