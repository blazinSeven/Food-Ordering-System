package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GoConnection {
    Connection coon = null;
    public Connection connect()
    {
        String databaseURL = "jdbc:postgresql://elmer.db.elephantsql.com:5432/hblzndoa";
        String user = "hblzndoa";
        String password = "PvfVbBZmOnuFI3haPfT3h4So7RhG_7Kk";
        try{
            Class.forName("org.postgresql.Driver");
        }catch (Exception e)
        {
            System.err.println("Fail to connect:"+e.getMessage());
        }
        try{
            coon = DriverManager.getConnection(databaseURL,user,password);

        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return coon;
    }
}
