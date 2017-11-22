package Search_Sort;

import database.GoConnection;
<<<<<<< HEAD
import javafx.scene.control.Label;
=======
>>>>>>> b96e74fe39ae0d25d8fe58db20868e85678f4637

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

<<<<<<< HEAD
=======
/**
 * Yinsheng Dong Nov 21
 */
>>>>>>> b96e74fe39ae0d25d8fe58db20868e85678f4637
public class SortByWaitingTime {
    public ArrayList<String> sortByWaitingTimeRestaurantName;
    public ArrayList<Integer> sortByWaitingTimeRestId;
    public ArrayList<String> sortByWaitingTimeAddress;
    public ArrayList<Float> sortByWaitingTimeRate;
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
                sortByWaitingTimeRestaurantName = new ArrayList<>();
                sortByWaitingTimeRestId = new ArrayList<>();
                sortByWaitingTimeRate = new ArrayList<>();
                sortByWaitingTimeAddress = new ArrayList<>();
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars from restaurants r, locations l, reviews rv WHERE r.user_id = l.user_id AND rv.restaurant_id = r.id GROUP BY r.id,r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars ORDER BY r.waiting_time";
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
                    sortByWaitingTimeRestaurantName.add(name);
                    sortByWaitingTimeRestId.add(id);
                    sortByWaitingTimeAddress.add(address);
                    sortByWaitingTimeRate.add(rate);

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
        SortByWaitingTime sortByWaitingTime = new SortByWaitingTime();
        sortByWaitingTime.sortByWaitingTime();
        System.out.println(sortByWaitingTime.sortByWaitingTimeRestId);
        System.out.println(sortByWaitingTime.sortByWaitingTimeRestaurantName);
<<<<<<< HEAD
        /**
        System.out.println(sortByWaitingTime.sortByWaitingTimeRestaurantName);
        System.out.println(sortByWaitingTime.sortByWaitingTimeAddress);
        System.out.println(sortByWaitingTime.sortByWaitingTimeRate);
        System.out.println(sortByWaitingTime.message);
**/
    }

=======
        System.out.println(sortByWaitingTime.sortByWaitingTimeAddress);
        System.out.println(sortByWaitingTime.sortByWaitingTimeRate);
        System.out.println(sortByWaitingTime.message);

    }
>>>>>>> b96e74fe39ae0d25d8fe58db20868e85678f4637
}
