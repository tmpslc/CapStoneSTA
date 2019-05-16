package capstone;
import java.sql.*;

public class database {
    public void Db(String args[])
    {
       final String DB_URl = "jdbc:mysql://localhost:3306/child?zeroDateTimeBehavior=convertToNull";
       final String SEL_QUERY = "select first_name, last_name from child";
       try (
               Connection connection = DriverManager.getConnection(DB_URl,"test","test"); 
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(SEL_QUERY) ){
           
       }
    catch (SQLException e)
    {
        e.printStackTrace();
}
    }
}
