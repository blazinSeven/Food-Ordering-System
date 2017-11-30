package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Written by Yinsheng Dong (yid164)
 * This function is using to add Menu of A restaurant
 */
public class AddMenu {

    /**
     * new connection
     */
    GoConnection connection = new GoConnection();

    /**
     * message to print out
     */
    public  String message = null;
    public String restaurantMessage = null;



    /**
     * The helper function to check the restaurant_id by using license_id
     * @param license_id
     */
    public int getRestaurantId (String license_id)
    {
        int restaurant_id = 0;
        if(license_id == null)
        {
            restaurantMessage = "licensed id can not be null";
        }
        else
        {
            connection.connect();
            try {
                if(connection.coon!=null)
                {
                    String checkRestIdQuery = "select id from restuarants where license_id = ?";
                    PreparedStatement checkRestppStmt = connection.coon.prepareStatement(checkRestIdQuery);
                    checkRestppStmt.setString(1,license_id);
                    ResultSet rs = checkRestppStmt.executeQuery();
                    if(!rs.next())
                    {
                        restaurantMessage = "Did not find the restaurant id";
                        connection.coon.close();
                    }
                    else
                    {
                        restaurantMessage = "The restaurant id has been found";
                        restaurant_id =  rs.getInt("id");
                        connection.coon.close();

                    }
                }
            }catch (SQLException e)
            {
                restaurantMessage = e.getMessage();
            }
        }
        return restaurant_id;
    }

    /**
     * The main part of this class, add menu information, using by restaurant_id and menuName
     * @param restaurant_id
     * @param menuName
     */
    public void AddMenu(int restaurant_id,String menuName)
    {
        if(menuName == null)
        {
            message = "menuName can not be null, please check";
            return;
        }
        else {
            connection.connect();
            try{
                if(connection.coon!=null)
                {
                    String checkQuery = "select menu_name from menus where restaurant_id = ? and menu_name = ?";
                    PreparedStatement checkppStmt = connection.coon.prepareStatement(checkQuery);
                    checkppStmt.setInt(1,restaurant_id);
                    checkppStmt.setString(2,menuName);
                    ResultSet checkResult = checkppStmt.executeQuery();
                    String checkQuery1 = "select id from restaurants where id = ?";
                    PreparedStatement checkppStmt1 = connection.coon.prepareStatement(checkQuery1);
                    checkppStmt1.setInt(1,restaurant_id);
                    ResultSet checkResult1 = checkppStmt1.executeQuery();
                    if(checkResult.next())
                    {
                        message = "This menu name has already used";
                        connection.coon.close();
                    }
                    else if(!checkResult1.next())
                    {
                        message = "Did not find the restaurant id";
                        connection.coon.close();
                    }
                    else {
                        String addMenuQuery = "INSERT INTO menus (restaurant_id, menu_name) VALUES (?,?)";
                        PreparedStatement ppStmt = connection.coon.prepareStatement(addMenuQuery);
                        ppStmt.setInt(1, restaurant_id);
                        ppStmt.setString(2, menuName);
                        int affected = ppStmt.executeUpdate();
                        if (affected > 0) {
                            message = "You have successfully add the menu "+menuName;
                            System.out.println(message);
                            connection.coon.close();
                        }
                    }

                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Testing function
     * @param args
     */
    public static void main(String args[])
    {
        AddMenu addMenu = new AddMenu();
        addMenu.AddMenu(1, "Apple Chese");
        System.out.println(addMenu.message);
    }
}
