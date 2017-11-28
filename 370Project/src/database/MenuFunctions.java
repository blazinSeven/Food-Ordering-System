package database;

import Entities.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuFunctions {

    private Menu menu;
    public String message;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(String menuName, int restaurant_id)
    {
        GoConnection connection = new GoConnection();
        if(menuName == null)
        {
            message = "menuName can not be null, please check";
            return;
        }
        else
        {
            connection.connect();
            try
            {
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
                    else
                    {
                        String addMenuQuery = "INSERT INTO menus (restaurant_id, menu_name) VALUES (?,?)";
                        PreparedStatement ppStmt = connection.coon.prepareStatement(addMenuQuery);
                        ppStmt.setInt(1, restaurant_id);
                        ppStmt.setString(2, menuName);
                        int affected = ppStmt.executeUpdate();
                        if (affected > 0)
                        {
                            message = "You have successfully add the menu "+menuName;
                            System.out.println(message);
                            menu = new Menu(menuName, restaurant_id);
                            connection.coon.close();
                        }
                    }

                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void editMeus(String menuName, int restaurant_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String findQuery = "select * from menus where restaurant_id =" + restaurant_id;
                String query = "update menus set menu_name = ? where restaurant_id = ?";
                Statement findStatement = connection.coon.prepareStatement(findQuery);
                ResultSet findResult = findStatement.executeQuery(findQuery);
                if(findResult.next())
                {
                    PreparedStatement statement = connection.coon.prepareStatement(query);
                    statement.setString(1,menuName);
                    statement.setInt(2,restaurant_id);
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next())
                    {
                        message = "edit succeed";
                        menu = new Menu(menuName,restaurant_id);
                    }

                }

            }catch(SQLException e)
            {

            }
        }
    }
}
