package business;

import business.interfaces.Service;
import datastorage.repositories.NodeRepository;
import model.Node;
import model.request.NodeAdditionRequest;

public class NodeAdditionService implements Service<NodeAdditionRequest> {

    NodeRepository nodeRepository;

    public NodeAdditionService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node Execute(NodeAdditionRequest request) {
       // System.out.println(request.user.getId());

        try {
            return nodeRepository.create(new Node(request.name,request.x,request.y,request.configId));

        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }
}
