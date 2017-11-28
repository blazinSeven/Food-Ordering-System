package Search_Sort;

/**
 * Create By Hao Li at Nov.28th
 */

import database.GoConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SortByOrdering {
    public ArrayList<String> sortByOrderingRestaurantName;
    public ArrayList<Integer> sortByOrderingRestId;
    public ArrayList<String> sortByOrderingAddress;
    public ArrayList<Float> sortByOrderingRate;
    public String message = "";
    private String name = "";
    private String address = "";
    private int id = 0;
    private float rate = 0;

    public void sortByWaitingTime()
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                sortByOrderingRestaurantName = new ArrayList<>();
                sortByOrderingRestId = new ArrayList<>();
                sortByOrderingRate = new ArrayList<>();
                sortByOrderingAddress = new ArrayList<>();
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars from restaurants r, locations l, reviews rv WHERE r.user_id = l.user_id AND rv.restaurant_id = r.id GROUP BY r.id,r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars ORDER BY r.id";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    name = resultSet.getString(2);
                    id = resultSet.getInt(1);
                    address = resultSet.getInt(3)+" "+
                            resultSet.getString(4)+" "+
                            resultSet.getString(5)+ " "+
                            resultSet.getString(6);
                    rate = resultSet.getFloat(7);
                    sortByOrderingRestaurantName.add(name);
                    sortByOrderingRestId.add(id);
                    sortByOrderingAddress.add(address);
                    sortByOrderingRate.add(rate);

                }
                connection.coon.close();

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }

    }
}
