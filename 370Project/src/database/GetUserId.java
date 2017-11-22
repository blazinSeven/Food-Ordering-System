package database;
/**Create by Yinsheng Dong Nov 21
 *
 */

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserId {
    public String message ="";
    public int user_id;

    public void getUserId(String user_name, String password)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try {
                String query = "SELECT id FROM users WHERE username = ? AND passwords = ?";
                PreparedStatement ppstmt = connection.coon.prepareStatement(query);
                ppstmt.setString(1,user_name);
                ppstmt.setString(2,password);
                ResultSet resultSet = ppstmt.executeQuery();
                if(resultSet.next())
                {
                    user_id = resultSet.getInt(1);
                    message = "Good For now   "+ user_id;
                    connection.coon.close();
                }
                else
                {
                    message = "Can find it";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }

    public static void main(String arg[])
    {
        GetUserId getUserId = new GetUserId();
        getUserId.getUserId("ayden","12345");
        System.out.println(getUserId.message);
    }
}
