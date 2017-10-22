package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddUser {
    public GoConnection connect = new GoConnection();
    public String st = null;
    public String un = null;
    public String message = "haha";

    public void addUser(String username, String userPassword, String status)
    {
        connect.connect();
        if(connect.coon != null)
        {

            try {
                String checkQuery = "select username from users where username = ?";
                PreparedStatement checkStmt = connect.coon.prepareStatement(checkQuery);
                checkStmt.setString(1,username);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next())
                {
                    message = "This username has been used";
                    System.out.println(message);
                    connect.coon.close();
                }


                else
                {
                    String insertQuery = "insert into users (username, passwords, user_status) values(?,?,?)";
                    PreparedStatement ppStmt = connect.coon.prepareStatement(insertQuery);
                    un = username;
                    st = status;
                    ppStmt.setString(1, username);
                    ppStmt.setString(2, userPassword);
                    ppStmt.setString(3, status);
                    int affected = ppStmt.executeUpdate();
                    if (affected > 0) {
                        message = "You have successfully signed up";
                        System.out.println(message);
                        connect.coon.close();
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main (String arg[])
    {
        AddUser addUser = new AddUser();
        addUser.connect.connect();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        System.out.println("Please enter your status: \n");
        String status = in.nextLine();
        addUser.addUser(username,password,status);

    }


}
