package Sort;

import database.GoConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Yinsheng Dong Nov 28
 */
public class SortByRatingWithoutLogin {
    private ArrayList<SortInfo> sortInfos;
    private int rest_id = 0;
    private String rest_name = "";
    private String rest_address = "";
    private Time rest_time = null;
    private float rest_rate = 0;
    private float rest_avg_price = 0;
    public String message = "";

    public ArrayList<SortInfo> getSortByRatingWithoutLogin() {
        return sortInfos;
    }

    private void sortbyRatingWithoutLogin(String city, String province)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        sortInfos = new ArrayList<>();
        if(connection.coon!=null)
        {
            try{
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city,l.province, r.waiting_time, rv.stars, r.avg_price \n" +
                        "                        from restaurants r\n" +
                        "                        left join locations l on l.user_id = r.user_id\n" +
                        "                        left join reviews rv on rv.restaurant_id = r.id\n" +
                        "                        WHERE l.city = ?\n" +
                        "                        AND l.province = ?\n";
                PreparedStatement statement = connection.coon.prepareStatement(query);
                statement.setString(1,city);
                statement.setString(2,province);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    rest_id = resultSet.getInt(1);
                    rest_name = resultSet.getString(2);
                    rest_address = resultSet.getInt(3)+" "+
                            resultSet.getString(4)+" "+
                            resultSet.getString(5)+" "+
                            resultSet.getString(6);
                    rest_time = resultSet.getTime(7);
                    rest_rate = resultSet.getFloat(8);
                    rest_avg_price = resultSet.getFloat(9);
                    SortInfo sortInfo = new SortInfo(rest_id,rest_name,rest_address,rest_time,rest_rate,rest_avg_price);
                    sortInfos.add(sortInfo);
                }
                connection.coon.close();
                Collections.sort(sortInfos, SortInfo.rateComparator);
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }else {
            message = "lost connection";
        }
    }

    public SortByRatingWithoutLogin(String city, String province)
    {
        sortbyRatingWithoutLogin(city,province);
    }


    public static void main(String arg[])
    {
        SortByRatingWithoutLogin sortByRatingWithoutLogin = new SortByRatingWithoutLogin("Saskatoon","SK");
        System.out.println(sortByRatingWithoutLogin.getSortByRatingWithoutLogin());
    }
}
