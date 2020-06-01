package business;

import business.interfaces.Service;
import datastorage.repositories.EdgeRepository;
import model.Edge;

import java.sql.SQLException;

public class EdgeAdditionService implements Service<Edge> {

    EdgeRepository edgeRepository;

    public EdgeAdditionService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    @Override
    public Edge Execute(Edge request) {

        try {
            return edgeRepository.create(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
