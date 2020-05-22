package datastorage;

import model.Subscription;
import model.User;
import model.UserRole;

import java.time.LocalDate;
import java.util.Date;

public class UserRepository {

    public User GetUser (String username, String password)
    {
        var user = new User();
        var subscription = new Subscription();

        subscription.setEndDate(LocalDate.now().plusDays(2));

        user.setUserRole(UserRole.customer);
        user.setSubscription(subscription);
        user.setEmail("email@boss.com");
        user.setCompany("fereastra@monopol.all");

        return user;

    }



}
