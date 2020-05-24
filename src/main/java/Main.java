import controller.LoginController;
import datastorage.repositories.ConfigurationRepository;
import datastorage.repositories.NodeRepository;
import datastorage.repositories.SubscriptionRepository;
import datastorage.repositories.UserRepository;
import model.Configuration;
import model.Node;
import model.User;
import model.enums.UserRole;
import view.LoginView;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {



        try{
            //LoginController loginController = new LoginController(new LoginView(),new UserRepository());
            var noderepo= new NodeRepository();

            var node = new Node("name",255,333,2);

            var conf = noderepo.create(node);



            System.out.println(conf.getId());



        }
        catch (Exception ex){
            System.out.println(ex.getMessage());

        }

    }
}
