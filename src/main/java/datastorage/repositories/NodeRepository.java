package datastorage.repositories;

import datastorage.DbConnection;
import model.Node;

import java.sql.SQLException;
import java.util.ArrayList;

public class NodeRepository extends BaseRepository {

    public NodeRepository() throws SQLException {
        super(DbConnection.getInstance());
    }

    public Node create(Node node) throws SQLException {
        var params = new ArrayList<>();
        params.add(node.getName());
        params.add(node.getX());
        params.add(node.getY());
        params.add(node.getConfigId());

        var resultSet = CallStoredProcedure("{call add_node(?,?,?,?)}", params);
        resultSet.next();
        node.setId(resultSet.getInt(1));

        return node;
    }

    public ArrayList<Node> getNodesByConfigurationId(Integer configurationId) throws SQLException {
        var result = new ArrayList<Node>();
        var resultSet = CallStoredProcedure("{call get_all_nodes_from_configuration(?)}",configurationId);

        while(resultSet.next())
        {
            result.add(new Node(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),
                                resultSet.getInt(4),resultSet.getInt(5)));
        }

        return result;
    }

}
