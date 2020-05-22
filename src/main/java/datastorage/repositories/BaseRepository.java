package datastorage.repositories;

import datastorage.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseRepository {

    Connection connection;

    public BaseRepository(DbConnection dbConnection)
    {
        connection = dbConnection.getConnection();
    }

    ResultSet CallStoredProcedure(String stringCall, ArrayList<Object> params) throws SQLException {
        var statement = connection.prepareCall(stringCall);

        for(int index = 0;index<params.size();index++){
            if(params.get(index) instanceof String){
                statement.setString(index+1,(String) params.get(index));
            }
            else if(params.get(index-1) instanceof Integer){
                statement.setInt(index+1,(Integer) params.get(index));
            }
        }

        statement.execute();

        return statement.getResultSet();

    }



}
