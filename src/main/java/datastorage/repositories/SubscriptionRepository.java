package datastorage.repositories;

import datastorage.DbConnection;
import model.Subscription;

import java.sql.SQLException;

public class SubscriptionRepository extends BaseRepository {

    public SubscriptionRepository() throws SQLException {
        super(DbConnection.getInstance());
    }

    public Subscription getByUserId(int userId) throws SQLException {
        var resultSet = CallStoredProcedure("{call get_subscription_by_user_id(?)}", userId);
        resultSet.next();

        return new Subscription( resultSet.getInt(1),resultSet.getDate(2).toLocalDate());

    }
}
