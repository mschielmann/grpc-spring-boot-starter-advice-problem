package example;

import example.proto.ExampleMethodRequest;
import example.proto.ExampleMethodResponse;
import example.proto.ExampleService2Grpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class Service2 extends ExampleService2Grpc.ExampleService2ImplBase {

    @Override
    public void exampleMethod(ExampleMethodRequest request,
                              StreamObserver<ExampleMethodResponse> responseObserver) {
        System.out.println("Service2");
        responseObserver.onError(new RuntimeException());
    }
}
