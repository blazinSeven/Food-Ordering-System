package database;
import Entities.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Scanner;
/**
 * Created by Kocur on 2017-10-25.
 * Fix by Yinsheng on Nov 8 2017
 */
public class AddOrderLines {

    GoConnection connection = new GoConnection();
    //final Food newFood;
    public AddUser addFood = null;
    public String message = null;
    //Orderlines have...id, order_id, quantity, price_per_one, price_total, discount_total

    public void setOrderLineInfo(int order_id, int dishes_id, int quantity, float discount_total)
    {
        float price_per_one = 0;
        float price_total = 0;
        //try and connect to our server, if the connection isn't null, procceed, else check exception from SQL api
        connection.connect();
        if (connection.coon != null) {
            try {
                //These lines setup SQL queries & check handle problematic cases using SQL API
                // Changed the total price that can be multiby quanity and unit price
                String addQuery = "INSERT INTO order_lines(order_id, dishes_id, quanity, price_per_one, price_total, discount_total)" +
                        "VALUES (?,?,?,?,?,?)";
                String getDishPrice = "SELECT dish_prices FROM dishes WHERE id = ?";
                PreparedStatement priceStmt = connection.coon.prepareStatement(getDishPrice);
                priceStmt.setInt(1, dishes_id);

                ResultSet results1 = priceStmt.executeQuery();
                if (results1.next()) {
                    price_per_one = results1.getFloat(1);
                    price_total = price_per_one * quantity;
                    PreparedStatement ppStmt = connection.coon.prepareStatement(addQuery);
                    ppStmt.setInt(1, order_id);
                    ppStmt.setInt(2, dishes_id);
                    ppStmt.setInt(3, quantity);
                    ppStmt.setFloat(4, price_per_one);
                    ppStmt.setFloat(5, price_total);
                    ppStmt.setFloat(6, discount_total);
                    int affected = ppStmt.executeUpdate();
                    if (affected > 0) {
                        message = "The dish information has been saved!";
                        System.out.println(message);
                        connection.coon.close();
                    } else {
                        System.out.println("There is no order_line to add");
                        connection.coon.close();
                    }
                }
                else
                {
                    message = "there is no order_line to add";
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {

        AddOrderLines addOrderLines = new AddOrderLines();
        addOrderLines.setOrderLineInfo(1,16,30,5);

    }
    



}
