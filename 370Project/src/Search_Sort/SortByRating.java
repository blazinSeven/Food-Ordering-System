package Search_Sort;

/**
 * Yinsheng Dong Nov 21`
 */

import database.GoConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SortByRating {
    public ArrayList<String> sortByRateRestaurantName;
    public ArrayList<Integer> sortByRateRestId;
    public ArrayList<String> sortByRateRestAddress;
    public ArrayList<Float> sortByRateRestRate;
    public String message = "";
    private String name = "";
    private String address = "";
    private int id = 0;
    private float rate = 0;

    public void sortByRate()
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                sortByRateRestaurantName = new ArrayList<>();
                sortByRateRestId = new ArrayList<>();
                sortByRateRestRate = new ArrayList<>();
                sortByRateRestAddress = new ArrayList<>();
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars from restaurants r, locations l, reviews rv WHERE r.user_id = l.user_id AND rv.restaurant_id = r.id GROUP BY r.id,r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars ORDER BY rv.stars DESC";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    address = resultSet.getInt(3)+" "+
                            resultSet.getString(4)+" "+
                            resultSet.getString(5)+ " "+
                            resultSet.getString(6);
                    rate = resultSet.getFloat(7);
                    sortByRateRestaurantName.add(name);
                    sortByRateRestId.add(id);
                    sortByRateRestAddress.add(address);
                    sortByRateRestRate.add(rate);

                }
                connection.coon.close();

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }

    }

    public static void main(String arg[])
    {
        SortByRating sortByRating = new SortByRating();
        sortByRating.sortByRate();
        System.out.println(sortByRating.sortByRateRestId);
        System.out.println(sortByRating.sortByRateRestaurantName);
        System.out.println(sortByRating.sortByRateRestAddress);
        System.out.println(sortByRating.sortByRateRestRate);
    }
}
