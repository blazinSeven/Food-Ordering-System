/**
 * Created by Josh on October 22nd
 */

package database;
import Entities.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddDishes {
    GoConnection connection = new GoConnection();
    final Food newFood;
    public AddUser addFood = null;
    public String message = null;

    public AddDishes(Food newItem) {
        this.newFood = newItem;
    }


    public void setDishInfo(String menus_id) {
        connection.connect();
        if (connection.coon != null) {
            try {
                //These lines setup SQL queries & check handle problematic cases using SQL API
                String addQuery = "insert into dishes (dishes_id, menus_id, dishes_name, dishes_price, dishes_preperation_time) values (?,?,?,?,?)";
                String getUserId = "select id from dishers where dish_name = '" + addFood.un + "'";
                Statement stmt = connection.coon.createStatement();
                ResultSet results = stmt.executeQuery(getUserId);
                results.next();

                int dishesId = newFood.getDishesID();

                //Parsing food price into a string for query.
                Double stringPrice = newFood.getPrice();

                //Parsing food preptime into a string for query
                String prepString = newFood.getPrepTime().toString();


                PreparedStatement ppStmt = connection.coon.prepareStatement(addQuery);
                ppStmt.setInt(1, dishesId);
                ppStmt.setString(2, menus_id);
                ppStmt.setString(3, newFood.getName());
                ppStmt.setString(4, stringPrice.toString());
                ppStmt.setString(5, prepString);
                int affected = ppStmt.executeUpdate();
                if (affected > 0) {
                    message = "The customer information has been saved";
                    System.out.println(message);
                    connection.coon.close();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}
