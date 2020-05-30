package business.interfaces;


import model.request.NodeAdditionRequest;

public interface Service<T> {

    void Execute(T request);

    void Execute(NodeAdditionRequest request);
}
