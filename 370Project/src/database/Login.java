package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    GoConnection connection = new GoConnection();
    public String message = null;
    public String name = null;
    //user_account user = new user_account();

    public void login(String username, String password)
    {
        connection.connect();
        if(connection.coon!=null)
        {
            try {
                String queryString = "select * from users where username=? and passwords=?";
                PreparedStatement ppStmt = connection.coon.prepareStatement(queryString);
                ppStmt.setString(1,username);
                ppStmt.setString(2,password);
                ResultSet rs = ppStmt.executeQuery();
                if(!rs.next())
                {
                    message = "Wrong username or password";
                    System.out.println(message);
                    connection.coon.close();
                }
                else
                {
                    message = "true";
                    //user.loged_in();
                    name=username;
                    System.out.println(message);
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String arg[])
    {
        Login login = new Login();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        login.login(username,password);
    }
}
