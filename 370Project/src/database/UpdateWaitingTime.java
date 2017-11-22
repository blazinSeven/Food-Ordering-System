package database;

import java.sql.*;

public class UpdateWaitingTime {
    public String message = "";

    public void UpdateWaitingTime(Time waitingTime, int restaurant_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon != null)
        {
            try{
                String query = "UPDATE restaurants SET waiting_time = ? WHERE id = ?";
                PreparedStatement ppst = connection.coon.prepareStatement(query);
                ppst.setTime(1, waitingTime);
                ppst.setInt(2,restaurant_id);
                int value = ppst.executeUpdate();
                if(value>0)
                {
                    message = "the waiting time has been updated";
                    connection.coon.close();
                }
                else
                {
                    message= "the waiting time has not been update";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "loose the connect";
        }
    }

    public static void main(String arg[])
    {
        UpdateWaitingTime updateWaitingTime = new UpdateWaitingTime();
        updateWaitingTime.UpdateWaitingTime(Time.valueOf("00:08:00"), 7);
        System.out.println(updateWaitingTime.message);
    }

}
