package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Kocur on 2017-10-24.
 * Default creator, only need to use display method.
 */
public class DisplayMenus
{

    public ArrayList menusDisplay = null;
    GoConnection connection = new GoConnection();


    /**
     In: Nothing
     Out:  Nothing is returned, menus in the database are printed to the console.
           the function queries the sql database for the name of all food in the database.
     */
    public void displayAllMenus(int id)
    {
        connection.connect();
        if (connection.coon != null) {
            String selectQuery = "SELECT m.menu_name, r.id FROM restaurants r left join menus m on m.restaurant_id = "+id;
            try {
                Statement stmt = connection.coon.createStatement();
                ResultSet results = stmt.executeQuery(selectQuery);
                menusDisplay = new ArrayList<String>();
                int rowCount = 0;
                while (results.next()) {
                    menusDisplay.add(results.getString("menu_name"));
                    rowCount++;
                }
                System.out.println("Total Menus: " + rowCount);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("Error: A connection could not be established.  ");
        }
    }

    /**
     * Param: none
     *
     * The function prints out all items for a given menu
     */
    public void displayItem(int menuID)
    {
        connection.connect();
        if(connection.coon != null)
        {
            String selectQuery = "SELECT dish_name FROM menus m, dishes d WHERE d.id = m.menu_id";
            try
            {
                Statement stmt = connection.coon.createStatement();
                ResultSet results = stmt.executeQuery(selectQuery);
                menusDisplay = new ArrayList<String>();
                int rowCount = 0;
                while (results.next())
                {
                menusDisplay.add(results.getString("dish_name"));
                rowCount++;
                }

                System.out.println("Total Items: " + rowCount + " On the menu");

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }else
        {
        System.out.print("Error: A connection could not be established.  ");
        }

    }

    public static void main(String[] args)
    {
        DisplayMenus testMenu = new DisplayMenus();
        testMenu.displayAllMenus(1);
        testMenu.displayItem(5);

    }










}
