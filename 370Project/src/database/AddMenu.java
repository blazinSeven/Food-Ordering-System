package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMenu {
    GoConnection connection = new GoConnection();
    public  String message = null;
    public String message1 = null;
    public int rest_id;

    public void getRestaurantId (String license_id)
    {
        if(license_id == null)
        {
            message1 = "licensed id can not be null";
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
                        message1 = "Did not find the restaurant id";
                        connection.coon.close();
                    }
                    else
                    {
                        message1 = "The restaurant id has been found";
                        rest_id =  rs.getInt("id");
                        connection.coon.close();

                    }
                }
            }catch (SQLException e)
            {
                message1 = e.getMessage();
            }
        }
    }

    public void AddMenu(int restuarant_id,String menuName)
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
                    checkppStmt.setInt(1,restuarant_id);
                    checkppStmt.setString(2,menuName);
                    ResultSet checkResult = checkppStmt.executeQuery();
                    String checkQuery1 = "select id from restaurants where id = ?";
                    PreparedStatement checkppStmt1 = connection.coon.prepareStatement(checkQuery1);
                    checkppStmt1.setInt(1,restuarant_id);
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
                        ppStmt.setInt(1, restuarant_id);
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

    public static void main(String args[])
    {
        AddMenu addMenu = new AddMenu();
        addMenu.AddMenu(1, "SANDWICHES & WRAPS");
        System.out.println(addMenu.message);
    }
}
