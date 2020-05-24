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
}
