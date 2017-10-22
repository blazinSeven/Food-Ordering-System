package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddLocation {

    public String message = null;
    GoConnection connection = new GoConnection();
    public void addLocations(int user_id, int house_num, String street, String city, String province, String post_code)
    {
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String checkUserId = "select id from users where id = ?";
                PreparedStatement ppCheckStmt = connection.coon.prepareStatement(checkUserId);
                ppCheckStmt.setInt(1,user_id);
                ResultSet checkResult = ppCheckStmt.executeQuery();
                if(!checkResult.next())
                {
                    message = "Can not find this user";
                    connection.coon.close();
                }
                else
                {
                    String addLocation = "insert into locations values";
                }
            }catch (SQLException e)
            {

            }
        }
    }
}
