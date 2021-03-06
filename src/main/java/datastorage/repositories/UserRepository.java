package datastorage.repositories;

import datastorage.DbConnection;
import model.User;
import model.enums.UserRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public User create(User user) throws SQLException {
        var params = new ArrayList<Object>();
        params.add(user.getUserRole());
        params.add(user.getEmail());
        params.add(user.getPassword());
        params.add(user.getCompany());
        params.add(user.getAge());

        var resultSet = CallStoredProcedure("{call add_user(?,?,?,?,?)}", params);
        resultSet.next();
        user.setId(resultSet.getInt(1));

        return user;

    }

    public List<List<Object>> getAllUsersAsString() throws SQLException {
        var params = new ArrayList<Object>();
        List<List<Object>> result= new ArrayList<>();
        List<Object> row =  new ArrayList<>();

        var resultSet = CallStoredProcedure("{call get_all_users()}",params);

        while (resultSet.next()){
            row = new ArrayList<>() ;

            row.add(resultSet.getInt(1));
            row.add(UserRole.values()[resultSet.getInt(2)].name());
            row.add(resultSet.getString(3));
            row.add(resultSet.getString(4));
            row.add(resultSet.getString(5));
            row.add(resultSet.getInt(6));

            result.add(row);
        }

        return result;
    }

    public void delete(Integer userId) throws SQLException {
        var params = new ArrayList<Object>();
        params.add(userId);

        CallStoredProcedure("{call delete_user(?)}", params);

    }



}
