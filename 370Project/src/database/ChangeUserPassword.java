package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create by Yinsheng Dong (yid164) at Nov 21
 */
public class ChangeUserPassword {

    public String message = "";
    public String verifyMessage ="";
    public void changePassword(int user_id, String new_password)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null) {
            try {
                String query = "UPDATE users SET passwords = ? WHERE id = ?";
                PreparedStatement ppstmt = connection.coon.prepareStatement(query);
                ppstmt.setString(1, new_password);
                ppstmt.setInt(2, user_id);
                int value = ppstmt.executeUpdate();
                if (value > 0) {
                    message = "The user password has been changed";
                    connection.coon.close();
                } else {
                    message = "The password has been not been changed";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "loose connection";
        }
    }

    public void verifyPassword(int user_id, String old_password)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        String op = "";
        if(connection.coon != null)
        {
            try{
                String query = "SELECT passwords FROM users WHERE id="+user_id;
                Statement stmt = connection.coon.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                if(resultSet.next())
                {
                    op = resultSet.getString(1);
                    if(op.equals(old_password))
                    {
                        verifyMessage = "Y";
                    }
                    else
                    {
                        verifyMessage = "N";
                    }
                    connection.coon.close();
                }
                else
                {
                    message = "can not find this user";
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
        ChangeUserPassword changeUserPassword = new ChangeUserPassword();
        /**
        changeUserPassword.verifyPassword(1,"ayden");
         **/
        changeUserPassword.changePassword(1,"12345");
        System.out.println(changeUserPassword.verifyMessage);
        System.out.println(changeUserPassword.message);
    }
}
