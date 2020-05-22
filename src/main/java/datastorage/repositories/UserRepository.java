package datastorage.repositories;

import datastorage.DbConnection;
import model.User;
import model.UserRole;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository extends BaseRepository {


    public UserRepository() throws SQLException {
        super(DbConnection.getInstance());
    }

    public User get(String user, String password) throws SQLException {
        var params = new ArrayList<Object>();
        params.add(user);
        params.add(password);

        var resultSet = CallStoredProcedure("{call get_user_by_email_and_password(?,?)}", params);
        resultSet.next();

        return new User(
                resultSet.getInt(1),
                UserRole.values()[resultSet.getInt(2)],
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getInt(6));



        /*while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        */

    }



}
