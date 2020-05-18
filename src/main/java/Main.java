import java.sql.*;
public class Main {
    public static void main(String[] args)
    {
        try{
            String user = "root";
            String password = "";
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/nts",user,password);

            var statement = connection.prepareCall("{call add_user(?,?)}");
            statement.setString(1,"mail");
            statement.setInt(2,2);

            statement.execute();


            boolean execute = statement.execute("{call add_user()}");


        }
        catch (Exception ex){

        }

    }
}
