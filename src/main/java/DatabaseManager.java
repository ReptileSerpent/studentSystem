import java.sql.*;

public class DatabaseManager
{
    public static void checkConnection()
    {
        try (var connection = DriverManager.getConnection("jdbc:mariadb://localhost/", "write username here", "write password here"))
        {
            try (var statement = connection.createStatement())
            {
                statement.executeQuery("use test_database;");
                System.out.println("use test_database command successful");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Tuple<Boolean, Connection> tryGetConnection()
    {
        try
        {
            return new Tuple<>(true, DriverManager.getConnection("jdbc:mariadb://localhost/", "write username here", "write password here"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new Tuple<>(false, null);
        }
    }
}
