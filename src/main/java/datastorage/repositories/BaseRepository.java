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
            else if(params.get(index) instanceof Integer){
                statement.setInt(index+1,(Integer) params.get(index));
            }
            else if(params.get(index) instanceof Enum){
                statement.setInt(index+1,((Enum) params.get(index)).ordinal());
            }

        }

        statement.execute();

        return statement.getResultSet();

    }

    ResultSet CallStoredProcedure(String stringCall,Object param) throws SQLException {
        var statement = connection.prepareCall(stringCall);

        if(param instanceof String){
                statement.setString(1,(String) param);
            }
            else if(param instanceof Integer){
                statement.setInt(1,(Integer) param);
            }
            else if(param instanceof Enum){
            statement.setInt(1,(((Enum) param).ordinal()));
            }

        statement.execute();

        return statement.getResultSet();

    }



}
