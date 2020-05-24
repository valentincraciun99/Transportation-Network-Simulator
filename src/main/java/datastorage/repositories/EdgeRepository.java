package datastorage.repositories;

import datastorage.DbConnection;
import model.Edge;

import java.sql.SQLException;
import java.util.ArrayList;

public class EdgeRepository extends BaseRepository{
    public EdgeRepository() throws SQLException {
        super(DbConnection.getInstance());
    }
    //TODO: VERIFY IN SQL STORED PROCEDURE THAT NODE_FROM != NODE_TO WHEN CREATE A EDGE
    public Edge create(Edge edge) throws SQLException {
        var params = new ArrayList<Object>();
        params.add(edge.getNode_from());
        params.add(edge.getNode_to());
        params.add(edge.getValue());
        params.add(edge.getConfigId());

        var resultSet = CallStoredProcedure("{call add_edge(?,?,?,?)}", params);
        resultSet.next();
        edge.setId(resultSet.getInt(1));

        return edge;
    }

    public ArrayList<Edge> getAllEdgesFromConfiguration(Integer config_d) throws SQLException {
        var result = new ArrayList<Edge>();

        var resultSet =  CallStoredProcedure("{call get_all_edges_from_configuration(?)}", config_d);

        while(resultSet.next())
        {
            result.add(new Edge(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)
                        ,resultSet.getInt(4),resultSet.getInt(5)));
        }

        return  result;
    }

}
