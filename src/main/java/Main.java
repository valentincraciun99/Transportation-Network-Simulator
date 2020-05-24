import datastorage.repositories.*;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {



        try{
            //LoginController loginController = new LoginController(new LoginView(),new UserRepository());
            var edgeRepo= new NodeRepository();

            var nodes = edgeRepo.getAllNodesFromConfiguration(2);

            for(var node:nodes) {
                System.out.println(node.getId());
            }


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());

        }

    }
}
