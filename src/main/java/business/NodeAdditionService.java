package business;

import business.interfaces.Service;
import model.User;
import model.request.NodeAdditionRequest;

public class NodeAdditionService implements Service<NodeAdditionRequest> {



    @Override
    public void Execute(NodeAdditionRequest request) {
       // System.out.println(request.user.getId());


    }
}
