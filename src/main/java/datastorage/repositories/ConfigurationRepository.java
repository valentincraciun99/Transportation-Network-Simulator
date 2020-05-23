package datastorage.repositories;

import datastorage.DbConnection;
import model.Configuration;

import java.sql.SQLException;

public class ConfigurationRepository extends BaseRepository {

    public ConfigurationRepository() throws SQLException {
        super(DbConnection.getInstance());
    }

    public Configuration getByUserId(int userId) throws SQLException {
        var resultSet = CallStoredProcedure("{call get_configuration_by_user_id(?)}", userId);
        resultSet.next();

        return new Configuration(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3));
    }
}
