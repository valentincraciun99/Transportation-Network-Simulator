package business.interfaces;


import model.Node;
import model.request.NodeAdditionRequest;

public interface Service<T> {

    Object Execute(T request);
}
